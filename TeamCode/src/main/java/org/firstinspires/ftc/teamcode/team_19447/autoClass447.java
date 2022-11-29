package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

public class autoClass447 {

    DcMotor FrontLeft;
    DcMotor RearLeft;
    DcMotor FrontRight;
    DcMotor RearRight;
    public static double forwardTicks;
    public static double strafeTicks;

    public autoClass447(double forward, double strafe, DcMotor FL, DcMotor BL, DcMotor FR, DcMotor BR) {
        forwardTicks = forward;
        strafeTicks = strafe;
        FrontLeft = FL;
        RearLeft = BL;
        FrontRight = FR;
        RearRight = BR;
    }

    //FUNCTIONS
    public void StrafeRight(int distanceCM, boolean NextSequence) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        /*
        Strafe Right
        FL: Forward
        FR: Reverse
        RL: Reverse
        RR: Forward
        */

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {

        }
    }

    public void StrafeLeft(int distanceCM, boolean NextSequence) {

        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        /*
        Strafe Left
        FL: Reverse
        FR: Forward
        RL: Forward
        RR: Reverse
         */

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void Forward(int distanceCM, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearLeft.setPower(0);
        RearRight.setPower(0);
    }

    public void Backward(int distanceCM, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double errorLeft = Math.abs(FrontLeft.getTargetPosition() - FrontLeft.getCurrentPosition());
        double errorRight = Math.abs(FrontRight.getTargetPosition() - FrontRight.getCurrentPosition());
        double p = 0.5;

        FrontLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        FrontRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));
        RearLeft.setPower(Range.clip(errorLeft * p, -0.4, 0.4));
        RearRight.setPower(Range.clip(errorRight * p, -0.4, 0.4));

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void TurnLeft(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void TurnRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void forwardDiagonalLeft(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(0);
        FrontRight.setTargetPosition(ticks);
        RearLeft.setTargetPosition(ticks);
        RearRight.setTargetPosition(0);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void forwardDiagonalRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(ticks);
        FrontRight.setTargetPosition(0);
        RearLeft.setTargetPosition(0);
        RearRight.setTargetPosition(ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void backwardDiagonalLeft(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(-ticks);
        FrontRight.setTargetPosition(0);
        RearLeft.setTargetPosition(0);
        RearRight.setTargetPosition(-ticks);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void backwardDiagonalRight(int distanceCM, double Speed, int Tolerance, boolean NextSequence) {
        // convert encoder ticks to centimetres
        double tick = distanceCM * forwardTicks;
        int ticks = (int) tick;

        ((DcMotorEx) FrontLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) FrontRight).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearLeft).setTargetPositionTolerance(Tolerance);
        ((DcMotorEx) RearRight).setTargetPositionTolerance(Tolerance);

        FrontLeft.setTargetPosition(0);
        FrontRight.setTargetPosition(-ticks);
        RearLeft.setTargetPosition(-ticks);
        RearRight.setTargetPosition(0);

        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(Speed);
        FrontRight.setPower(Speed);
        RearLeft.setPower(Speed);
        RearRight.setPower(Speed);

        while (FrontLeft.isBusy() && FrontRight.isBusy() && RearLeft.isBusy() && RearRight.isBusy() && NextSequence) {
        }
    }

    public void ChassisReset() {
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    //is it possible to translate cm distance into the lift??

    public void setMode(DcMotor.RunMode mode) {
        FrontLeft.setMode(mode);
        RearLeft.setMode(mode);
        FrontRight.setMode(mode);
        RearRight.setMode(mode);
    }

}