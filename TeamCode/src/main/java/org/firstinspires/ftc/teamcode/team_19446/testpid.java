package team_19446;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class testpid extends LinearOpMode {
    @Override
    public void runOpMode() {

        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse right side motors
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        //Intake
        DcMotor Intake = hardwareMap.get(DcMotor.class, "intake");

        Intake.setDirection(DcMotorSimple.Direction.REVERSE);

        //Arm
        DcMotor Arm = hardwareMap.get(DcMotor.class, "arm");
        Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Carousel
        DcMotor Carousel = hardwareMap.get(DcMotor.class, "carousel");

        Carousel.setDirection(DcMotorSimple.Direction.REVERSE);

        //Turret
        DcMotor Turret = hardwareMap.get(DcMotor.class, "turret");
        Turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        

        while (opModeIsActive()) {
            
        }
    }

    
    public void setMode(DcMotor.RunMode mode) {
        LF.setMode(mode);
        LB.setMode(mode);
        RF.setMode(mode);
        RB.setMode(mode);
    }

    public void stopDriving() {
        LF.setPower(0);
        LB.setPower(0);
        RF.setPower(0);
        RB.setPower(0);
    }

    public void motorOn(double power) {
        LF.setPower(power);
        LB.setPower(power);
        RF.setPower(power);
        RB.setPower(power);
    }

    public void setVelF(double vel) {
        LF.setVelocity(vel);
        LB.setVelocity(vel);
        RF.setVelocity(vel);
        RB.setVelocity(vel);
    }

    public void setVelS(double vel) {
        LF.setVelocity(-vel);
        LB.setVelocity(vel);
        RF.setVelocity(vel);
        RB.setVelocity(-vel);
    }

    public void carousel(double power, int timeS) {
        Carousel.setPower(power);

        sleep(timeS);
    }

    public void turret(double power, double rev) {

        int gob = (int) (rev*turretticks);
        Turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Turret.setTargetPosition(gob);

        Turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Turret.setPower(power);

        stopDriving();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void intake(double timeS, int power) {
        Intake.setPower(1);

        sleep((long) timeS*1000);

        Intake.setPower(0);

    }

    public void arm(double power, double heightCM) {
        double height = 0;
        int sign = 0;
        if (heightCM > 0) {
            height = heightCM;
            sign = 1;
        } else if (heightCM < 0) {
            height = -1* heightCM;
            sign = -1;
        }
        double tick = angleTicks * 57.2958 * Math.acos(1-((height * height)/(2*(radius * radius))));
        int ticks = (int) tick * sign;

        Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Arm.setTargetPosition(ticks);

        Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Arm.setPower(power);



        Arm.setPower(0);
    }




    public void PID (direction direction, double CM, double coefficientproportional, double coefficientderivative) {
        double Cd = coefficientderivative; // pid coefficient for derivative control
        double Cp = coefficientproportional; // pid coefficient for proportional control
        double d = 0; // derivative
        double p = 0; // proportional

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET);

        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double lf = LF.getCurrentPosition();
        double lb = LB.getCurrentPosition();
        double rf = RF.getCurrentPosition();
        double rb = RB.getCurrentPosition();
        double lastError = 0;

        double tick;
        double def = 0;

        if (direction == direction.FRONT) {

            tick = CM * forwardticks;

            def = tick/3;

            while (lf != tick || lb != tick || rf != tick || rb != tick) {
                PIDTimer.reset(); //resets the timer
                
                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;

                setVelF(p + d);

                lastError = error;

            }


        }

        else if (direction == direction.BACK) {

            tick = CM * forwardticks;

            def = tick/3;

            while (lf != tick || lb != tick || rf != tick || rb != tick) {
                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * def * Cp;

                d = derivative * Cd * def;

                setVelF(-1 *(p + d);

                lastError = error;

            }

        }
        else if (direction == direction.LEFT) {

            tick = CM * strafeticks;
            def = tick/3;

            while (lf != -tick || lb != tick || rf != tick || rb != -tick) {

                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * Cp * def;

                d = derivative * Cd * def;

                setVelS(p + d);

                lastError = error;

            }

        }

        else if (direction == direction.RIGHT) {

            tick = CM * strafeticks;
            def = tick/3;

            while (lf != tick || lb != -tick || rf != -tick || rb != tick) {
                PIDTimer.reset(); //resets the timer

                lf = LF.getCurrentPosition();
                lb = LB.getCurrentPosition();
                rf = RF.getCurrentPosition();
                rb = RB.getCurrentPosition();

                double error = tick - (lf+lb+rf+rb)/4;

                double deltaError = error - lastError;
                double derivative = deltaError / PIDTimer.time();


                p = error * Cp;

                d = derivative * def * Cd;

                setVelS((p + d)*-1);

                lastError = error;

            }

        }
}