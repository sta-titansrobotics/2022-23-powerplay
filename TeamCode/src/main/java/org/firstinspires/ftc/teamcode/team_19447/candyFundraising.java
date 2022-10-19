//This code is meant for fundraising event

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        leftClaw.setPosition(0);
        rightClaw.setPosition(0);

        waitForStart();

        if (gamepad1.dpad_up) {
            Lift.setPower(1);
        }
        if (gamepad1.dpad_down) {
            Lift.setPower(-1);
        }

        //Set to 0 because it hasn't moved yet
        if (gamepad1.x) {





        }



    }
}
