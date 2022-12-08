// FINALIZED DRIVE CONTROLLED, DO NOT TOUCH ANYTHING HERE //

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp
public class ArmTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Servo RightArm = hardwareMap.get(Servo.class, "RightArm");
        Servo LeftArm = hardwareMap.get(Servo.class, "LeftArm");

        waitForStart();

        if (isStopRequested())
            return;

        while (opModeIsActive()) {

            if (gamepad2.left_bumper){
                RightArm.setPosition(-0.25); //set to 45 deg
                LeftArm.setPosition(0.25);
            }
            if (gamepad2.right_bumper){
                RightArm.setPosition(0.25); //set to 45 deg
                LeftArm.setPosition(-0.25);
            }

        }

    }
}
