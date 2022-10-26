package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


//This code is meant for Halloween fundraising event

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class candyoptimizedcontrol extends LinearOpMode {

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
        Lift.setPower(0);
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);

        waitForStart();

        //Lift will go up when right stick is moved up:
        if (gamepad1.right_stick_y == 100){
            Lift.setPower(1);
        }
        //Lift will go down when right stick is moved down:
        if (gamepad1.right_stick_y == 100){
            Lift.setPower(-1);
        }

        // one of these values are probably going to be negative
        // Servo Claw operations, controls have been changed, holding RB opens the claws, while releasing it closes them:
        if (gamepad1.right_bumper) { leftClaw.setPosition(0.75); // rotates 0.75*180 = 135 deg to the right
            rightClaw.setPosition(-0.75); // rotates 0.75*180 = 135 deg to the left
        } else { leftClaw.setPosition(0);
            rightClaw.setPosition(0);

        }


    }
}

