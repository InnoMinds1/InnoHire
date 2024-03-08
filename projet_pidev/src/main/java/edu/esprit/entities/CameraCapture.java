package edu.esprit.entities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CameraCapture {

    public static void startCameraCapture() {
        JFrame frame = new JFrame("Camera Capture");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFillArea(true);

        frame.setLayout(new BorderLayout());
        frame.add(webcamPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                webcam.close();
            }
        });
    }
}
