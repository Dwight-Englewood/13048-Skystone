package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;

//import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Hardware.Bot;
import org.firstinspires.ftc.teamcode.Hardware.*;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Gochu Tele", group="Teleop")

//@Disabled
public class GochuTele extends OpMode {

    Bot bot = new Bot();
    int TankDrive = -1;
    double factor = 0.75;
    double pos = 0.5;
    /*
    fr = 1\

    fl = 2
    bl = 3
    br = 4
     */

    @Override
    public void init() {
        bot.init(hardwareMap, telemetry, false);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

//        double lsx = gamepad1.left_stick_x;
        GameDrive drive = new GameDrive(bot);
        TankDrive tank = new TankDrive(bot);
        double leftStickY = (double) -gamepad1.left_stick_y;
        double rightTrigger = (double) gamepad1.right_trigger;
        double leftTrigger = (double) gamepad1.left_trigger;
        double rightStickX = (double) gamepad1.right_stick_x;
        double rightStickY = (double) -gamepad1.right_stick_y;
        double leftStickX = (double) gamepad1.left_stick_x;
        double leftTrigger2 = (double) gamepad2.left_trigger;
        double rightTrigger2 = (double) gamepad2.right_trigger;

        telemetry.addData("PID: ", bot.BL.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
        telemetry.update();
        drive.driveBot(-leftStickY, rightStickX, -rightTrigger, -leftTrigger, 0.0, 0.0, factor);
        if(gamepad1.a)
            factor = 0.35;
        if(gamepad1.b)
            factor = 0.9;
        if(gamepad2.dpad_up)
            bot.lift.setPower(1);
        else if(gamepad2.dpad_down)
            bot.lift.setPower(-1);
        else
            bot.lift.setPower(0);
        if(gamepad2.right_stick_x < 0) {
            bot.joint.setPosition(0.55);
            telemetry.addData(">", "0.5");
            telemetry.update();
        }
        else if(gamepad2.right_stick_x > 0) {
            bot.joint.setPosition(0.1);
            telemetry.addData(">", "0.1");
            telemetry.update();
        }
        if(gamepad2.b) {
            bot.clamp.setPosition(0.1);
            telemetry.addData(">", "0.1");
            telemetry.update();
        }
        else if(gamepad2.a) {
            bot.clamp.setPosition(1.0);
            telemetry.addData(">", "1.0");
            telemetry.update();
        }
        if(gamepad2.right_trigger > 0.0)
            bot.gochu.setPower(gamepad2.right_trigger);
        else if(gamepad2.left_trigger > 0.0)
            bot.gochu.setPower(-gamepad2.left_trigger);

        if(gamepad2.right_bumper) {
            bot.gochu.setPower(0.0);
        }
        if(gamepad1.x) {
            bot.hook.setPosition(1.0);
        }
        if(gamepad1.y) {
            bot.hook.setPosition(0.0x);
        }

//        if(gamepad2.a)
//            pos = 0.5;
//        if(gamepad2.b)
//            pos = -0.5;
//        if(gamepad2.left_bumper)
//            bot.i += pos;
//        if(gamepad2.right_bumper)
//            bot.d += pos;
//        if(gamepad2.y)
//            bot.p += pos;
//        if(gamepad2.x)
//            bot.changeRunMode(DcMotor.RunMode.RUN_USING_ENCODER);




//        if(gamepad2.right_stick_y > 0.15)
//            bot.FR.setPower((double) gamepad2.right_stick_y);
//        else
//            bot.FR.setPower(0.0);
//        if(gamepad2.left_stick_y > 0.15)
//            bot.FL.setPower((double) gamepad2.left_stick_y);
//        else
//            bot.FL.setPower(0.0);
//        double botTheta = bot.imu.getGyroRotation(AngleUnit.RADIANS);
        //The readings from the gyro are different from the reading needed for the field centric code, so we apply a function to fix it
//        botTheta = (botTheta < 0) ? -botTheta : 2 * Math.PI - botTheta;
//        botTheta = botTheta;
//        double lsx = -gamepad1.left_stick_x;
//        double lsy = -gamepad1.left_stick_y;

//        double theta = gamepad1.right_stick_x / 2;
//        SimpleMatrix powVector = bot.driveTrain.drive(lsx, lsy, theta, botTheta);



//        if (gamepad1.dpad_up) {
//            bot.dumperPivot.variableSafe(.5);
//        } else if (gamepad1.dpad_down) {
//            bot.dumperPivot.variableSafe(-.3);
//        } else {
//            bot.dumperPivot.variableSafe(0);
//        }

//        if (gamepad1.x) {
//            bot.dumperPivot.pivotScore();
//        } else {
//            bot.dumperPivot.pivotNotScore();
//        }

//        if (gamepad1.dpad_right) {
//            bot.intakeSlides.variableMove(.5);
//        } else if (gamepad1.dpad_left) {
//            bot.intakeSlides.variableMove(-.5);
//        } else {
//            bot.intakeSlides.variableMove(0);
//        }

//        if (gamepad1.left_trigger > .5) {
//            bot.intakeSlides.outtake();
//        } else if (gamepad1.right_trigger > .5) {
//            bot.intakeSlides.intake();
//        } else {
//            bot.intakeSlides.notake();
//        }

//        if (gamepad2.a) {
//            bot.intakeSlides.pivotDown();
//        } else if (gamepad2.b) {
//            bot.intakeSlides.pivotUp();
//        } else if (gamepad2.y) {
//            bot.intakeSlides.pivotMiddle();
//        }

    }

    @Override
    public void stop() {
        bot.stop();

    }
}