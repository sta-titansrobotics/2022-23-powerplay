package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class RollerFlipper extends LinearOpMode {
    DcMotor rollerFlipper = null;
    @Override
    public void runOpMode() {
        //initialize the motor
        rollerFlipper = hardwareMap.dcMotor.get("flipperMotor");
        //set motor direction
        rollerFlipper.setDirection(DcMotor.Direction.FORWARD);
        //use encoder or not? IDKKKK
        rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //set power
        double flipperMotor;

        //wait for start button to be pushed
        waitForStart();

        while (opModeIsActive()){

            double flipperPower = gamepad1.touchpad_finger_1_y;

            rollerFlipper.setPower(flipperPower);

        }


    }

}
