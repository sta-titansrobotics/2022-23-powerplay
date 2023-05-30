package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.team_19446.encoderAuto;

public class MovementTest extends LinearOpMode {

    //Initialize motors
    DcMotor motorFL;
    DcMotor motorBL;
    DcMotor motorFR;
    DcMotor motorBR;

    @Override
    public void runOpMode() {

        //HARDWARE MAPPING FOR MOTORS
        motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse left side motors
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

        Movement robot = new Movement();
        robot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        robot.forward(20, 1);
        robot.turnLeft(30, 0.5);
        robot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {
            telemetry.addData("encoder-front-left: ", motorFL.getCurrentPosition());
            telemetry.addData("encoder-rear-left: ", motorBL.getCurrentPosition());
            telemetry.addData("encoder-front-right: ", motorFR.getCurrentPosition());
            telemetry.addData("encoder-rear-right: ", motorBR.getCurrentPosition());
            telemetry.update();
        }
    }
}
