package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.hardware.DcMotor;

public class encoderAuto {

    DcMotor leftFront, leftRear, rightRear, rightFront;
    public static double forwardTicks;
    public static double strafeTicks;

    public encoderAuto(double forward, double strafe, DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB) {
        forwardTicks = forward;
        strafeTicks = strafe;
        leftFront = LF;
        leftRear = LB;
        rightFront = RF;
        rightRear = RB;
    }

    /**
     * Moves robot forward
     * @param distanceCM desired distance travelled
     */
    public void forward(double distanceCM) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(ticks);
        leftRear.setTargetPosition(ticks);
        rightRear.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(rightRear.isBusy() && rightFront.isBusy() && leftRear.isBusy() && leftFront.isBusy()) {
        }

        stopDriving();

        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void backward(double distanceCM) {

        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(-ticks);
        rightFront.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && rightFront.isBusy()) {

        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void leftStrafe(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(ticks);
        rightRear.setTargetPosition(-ticks);
        rightFront.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && rightFront.isBusy()) {

        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void rightStrafe(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(ticks);
        rightFront.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && rightFront.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnLeft(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && rightFront.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnRight(double distanceCM) {
        double tick = distanceCM * strafeTicks;
        int ticks = (int) tick;

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(-ticks);
        rightFront.setTargetPosition(-ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorOn(1);

        while(leftFront.isBusy() && leftRear.isBusy() && rightRear.isBusy() && rightFront.isBusy()) {
        }

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void motorOn(double power) {
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
    }

    public void stopDriving() {
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }

    public void setMode(DcMotor.RunMode mode) {
        leftFront.setMode(mode);
        leftRear.setMode(mode);
        rightFront.setMode(mode);
        rightRear.setMode(mode);
    }
}
