//This code is meant for Halloween fundraising event

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class candyFundraising extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Lift
        DcMotor Lift = hardwareMap.get(DcMotor.class, "Lift");
        Lift.setDirection(DcMotor.Direction.FORWARD);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Servo claws
        Servo leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        Servo rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        /* Note that:
        servo rotation has a range of 0-1 or 0-180 deg, where:
        setPosition(0) moves 0 degrees
        setPosition(0.5) moves 90 degrees
        setPosition(1) moves 180 degrees
         */

        //Set to 0 because it hasn't moved yet
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);

        waitForStart();

        //Lift will go up when d-pad up is pressed:
        if (gamepad1.dpad_up) {
            Lift.setPower(1);
        }
        //Lift will go down when d-pad down is pressed:
        if (gamepad1.dpad_down) {
            Lift.setPower(-1);
        }

        // one of these values are probably going to be negative
        // Opening the servo claws, using the button 'x' on the gamepad:
        if (gamepad1.x) {
            leftClaw.setPosition(0.75);
            rightClaw.setPosition(-0.75);
        }

        // Research on youtube says to set position to 1 but I don't see how that will work if we are trying to revert it back to its original position.
        // Closing the servo claws, using the button 'b' on the gamepad:
        if (gamepad1.b) {
           leftClaw.setPosition(0);
           rightClaw.setPosition(0);
        }


        }
    }
