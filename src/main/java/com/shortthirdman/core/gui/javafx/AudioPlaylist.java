package com.shortthirdman.core.gui.javafx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class AudioPlaylist extends Application {
  final Label currentlyPlaying = new Label();
  final ProgressBar progress = new ProgressBar();
  private ChangeListener<Duration> progressChangeListener;

  public static void main(String[] args) throws Exception { launch(args); }

  public void start(final Stage stage) throws Exception {
    stage.setTitle("Simple Audio Player");

    final StackPane layout = new StackPane();

    final List<String> params = getParameters().getRaw();
    final File dir = (params.size() > 0) ? new File(params.get(0)) : new File("C:\\Users\\Public\\Music\\Sample Music");
    if (!dir.exists() && dir.isDirectory()) {
      System.out.println("Cannot find audio source directory: " + dir);
    }

    final List<MediaPlayer> players = new ArrayList<MediaPlayer>();
    for (String file : dir.list(new FilenameFilter() {
      @Override public boolean accept(File dir, String name) {
        return name.endsWith(".mp3");
      }
    })) players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
    if (players.isEmpty()) {
      System.out.println("No audio found in " + dir);
      return;
    }
    
    final MediaView mediaView = new MediaView(players.get(0));
    final Button skip = new Button("Skip");
    final Button play = new Button("Pause");

    for (int i = 0; i < players.size(); i++) {
      final MediaPlayer player     = players.get(i);
      final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
      player.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
          player.currentTimeProperty().removeListener(progressChangeListener);
          mediaView.setMediaPlayer(nextPlayer);
          nextPlayer.play();
        }
      });
    }
    
    skip.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent actionEvent) {
        final MediaPlayer curPlayer = mediaView.getMediaPlayer();
        MediaPlayer nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
        mediaView.setMediaPlayer(nextPlayer);
        curPlayer.currentTimeProperty().removeListener(progressChangeListener);
        curPlayer.stop();
        nextPlayer.play();
      }
    });

    play.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent actionEvent) {
        if ("Pause".equals(play.getText())) {
          mediaView.getMediaPlayer().pause();
          play.setText("Play");
        } else {
          mediaView.getMediaPlayer().play();
          play.setText("Pause");
        }
      }
    });

    mediaView.mediaPlayerProperty().addListener(new ChangeListener<MediaPlayer>() {
      @Override public void changed(ObservableValue<? extends MediaPlayer> observableValue, MediaPlayer oldPlayer, MediaPlayer newPlayer) {
        setCurrentlyPlaying(newPlayer);
      }
    });
    
    mediaView.setMediaPlayer(players.get(0));
    mediaView.getMediaPlayer().play();
    setCurrentlyPlaying(mediaView.getMediaPlayer());

    Button invisiblePause = new Button("Pause");
    invisiblePause.setVisible(false);
    play.prefHeightProperty().bind(invisiblePause.heightProperty());
    play.prefWidthProperty().bind(invisiblePause.widthProperty());
    
    layout.setStyle("-fx-background-color: cornsilk; -fx-font-size: 20; -fx-padding: 20; -fx-alignment: center;");
    layout.getChildren().addAll(
      invisiblePause,
      VBoxBuilder.create().spacing(10).children(
        currentlyPlaying,
        HBoxBuilder.create().spacing(10).alignment(Pos.CENTER).children(skip, play, progress, mediaView).build()
      ).build()
    );
    progress.setMaxWidth(Double.MAX_VALUE);
    HBox.setHgrow(progress, Priority.ALWAYS);
    Scene scene = new Scene(layout, 600, 120);
    stage.setScene(scene);
    stage.show();
  }

  private void setCurrentlyPlaying(final MediaPlayer newPlayer) {
    progress.setProgress(0);
    progressChangeListener = new ChangeListener<Duration>() {
      @Override public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
        progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
      }
    };
    newPlayer.currentTimeProperty().addListener(progressChangeListener);

    String source = newPlayer.getMedia().getSource();
    source = source.substring(0, source.length() - ".mp3".length());
    source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
    currentlyPlaying.setText("Now Playing: " + source);
  }

  private MediaPlayer createPlayer(String aMediaSrc) {
    final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
    player.setOnError(new Runnable() {
      @Override public void run() {
        System.out.println("Media error occurred: " + player.getError());
      }
    });
    return player;
  }
}
