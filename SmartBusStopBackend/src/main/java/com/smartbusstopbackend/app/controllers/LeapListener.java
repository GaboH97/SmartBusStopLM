package com.smartbusstopbackend.app.controllers;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.InteractionBox;


public class LeapListener extends Listener{
	
	private Robot robot;

    public LeapListener() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInit(Controller controller) {
        System.out.println("Leap Motion Controller initialized");
    }

    @Override
    public void onConnect(Controller controller) {
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        // Emulate click (Minimum distance is 3cm)
        controller.config().setFloat("Gesture.ScreenTap.MinDistance", 30f);

    }

    @Override
    public void onDisconnect(Controller controller) {
        System.out.println("Leap Motion Disconnected");
    }

    @Override
    public void onExit(Controller controller) {
        System.out.println("Leap Motion Controller disconnected");
    }

    /**
     * Represents the main loop. LeapMotion transmits at a rate of 300 FPS
     */
    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        // Create a 2D interaction box
        InteractionBox interactionBox = frame.interactionBox();

        Finger indexFinger = getFinger(frame.fingers(), Type.TYPE_INDEX);

        if (indexFinger != null) {

            //Get stabilized position to avoid hand unstability  
            Vector fingerPos = indexFinger.stabilizedTipPosition();

            // Normalize finger position relative to interaction box
            Vector boxFingerPos = interactionBox.normalizePoint(fingerPos);

            // Get Screen size
            Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

            /*
			 * Scale mouse move over PC screen according to normalized finger position
			 * inside the interaction box
             */
            robot.mouseMove((int) (screenDim.width * boxFingerPos.getX()),
                    (int) (screenDim.height - screenDim.height * boxFingerPos.getY()));
        }

        for (Gesture gesture : frame.gestures()) {
            System.out.println("Gesture type " + gesture.type());
            if (gesture.type() == Gesture.Type.TYPE_CIRCLE) {
                CircleGesture circleGesture = new CircleGesture(gesture);
                //Gesture is clockwise
                if (circleGesture.pointable().direction().angleTo(circleGesture.normal()) <= Math.PI / 4) {
                    robot.mouseWheel(1);
                } else {
                    robot.mouseWheel(-1);
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

            } else if (gesture.type() == Gesture.Type.TYPE_SCREEN_TAP) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            } else if (gesture.type() == Gesture.Type.TYPE_SWIPE) {
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
            }
        }
    }

    /**
     *
     * @param fingerList
     * @param fingerType
     * @return
     */
    private Finger getFinger(FingerList fingerList, Type fingerType) {
        for (Finger finger : fingerList) {
            if (finger.type().equals(fingerType)) {
                return finger;
            }
        }
        return null;
    }
}
