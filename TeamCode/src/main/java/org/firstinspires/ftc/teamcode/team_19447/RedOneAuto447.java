package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class RedOneAuto447 extends LinearOpMode {

    // Set default motor power
    public static double forwardTicks = 52.3;
    public static double strafeTicks = 54.05;
    double power = 1;

    @Override
    public void runOpMode() {

        DcMotor leftFront = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor leftRear = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor rightRear = hardwareMap.get(DcMotor.class, "motorBackRight");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        encoders447 robot = new encoders447(forwardTicks, strafeTicks, leftFront, leftRear, rightFront, rightRear);
        robot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        // movement
        robot.forward(60);
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        sleep(10000);
        robot.turnLeft(10);


        robot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // add values to telemetry
        while (opModeIsActive()) {

            telemetry.addData("encoder-front-left: ", leftFront.getCurrentPosition());
            telemetry.addData("encoder-rear-left: ", leftRear.getCurrentPosition());
            telemetry.addData("encoder-front-right: ", rightFront.getCurrentPosition());
            telemetry.addData("encoder-rear-right: ", rightRear.getCurrentPosition());
            telemetry.update();
        }


    }


}


