package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Movement {

    DcMotor FL, FR, BL, BR;

    public void forward(double distance, double speed) {
        double dist = distance * 52.3;
        int cm = (int) dist;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(cm);
        FR.setTargetPosition(cm);
        BL.setTargetPosition(cm);
        BR.setTargetPosition(cm);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void backward(double distance, double speed) {
        double dist = distance * 52.3;
        int cm = (int) dist;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(-cm);
        FR.setTargetPosition(-cm);
        BL.setTargetPosition(-cm);
        BR.setTargetPosition(-cm);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void strafeLeft(double distance, double speed) {
        double dist = distance * 54.05;
        int cm = (int) dist;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(-cm);
        FR.setTargetPosition(cm);
        BL.setTargetPosition(cm);
        BR.setTargetPosition(-cm);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void strafeRight(double distance, double speed) {
        double dist = distance * 54.05;
        int cm = (int) dist;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(cm);
        FR.setTargetPosition(-cm);
        BL.setTargetPosition(-cm);
        BR.setTargetPosition(cm);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnLeft(double angle, double speed) {
        //targetEncoderPosition = angle (in deg) divided by 360 degrees times the counts per revolution (40:1 motor = 1120 counts)
        int turn = (int) ((angle / 360) * 1120);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(-turn);
        FR.setTargetPosition(turn);
        BL.setTargetPosition(-turn);
        BR.setTargetPosition(turn);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnRight(double angle, double speed) {
        //targetEncoderPosition = angle (in deg) divided by 360 degrees times the counts per revolution (40:1 motor = 1120 counts)
        int turn = (int) ((angle / 360) * 1120);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(turn);
        FR.setTargetPosition(-turn);
        BL.setTargetPosition(turn);
        BR.setTargetPosition(-turn);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(speed);

        //Loop and keep it busy
        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {

        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setMode(DcMotor.RunMode mode) {
        FL.setMode(mode);
        FR.setMode(mode);
        BL.setMode(mode);
        BR.setMode(mode);
    }

    public void stopDriving() {
        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }

    public void setPower(double power) {
        FL.setPower(power);
        FR.setPower(power);
        BL.setPower(power);
        BR.setPower(power);
    }
}
