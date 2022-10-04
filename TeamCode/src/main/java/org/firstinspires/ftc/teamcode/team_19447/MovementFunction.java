package org.firstinspires.ftc.teamcode.team_19447;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


    @TeleOp
    public class MovementFunction extends LinearOpMode {
        public static int movement() {
            // see if we can simply get a circumference of the wheel
            double p = 3.1415926535;
            double circumference = 10 * p; //this

            int speed = 1;

  //distance = speed x time

/*circumference of wheel = 31.4 cm
therefore every rotation of wheel = 31.4 cm
total wheel angle = 360 deg
every degree of the wheel = 10/360 = 1/36 cm of the rotation
                                   = 1/36 * 31.4
                                   = 0.872222222222222
Therefore the wheel will travel 0.872 cm for every degree rotation

for this measure we have to change to angles
 */


        }

    }
        @Override
        public void runOpMode() {

            //Moving
            DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
            DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
            DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
            DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

            motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
            motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

            motorFR.setPower(2.5);
            motorBR.setPower(2.5);
            motorFL.setPower(2.5);
            motorBL.setPower(2.5);
            sleep(300);


        }
    }