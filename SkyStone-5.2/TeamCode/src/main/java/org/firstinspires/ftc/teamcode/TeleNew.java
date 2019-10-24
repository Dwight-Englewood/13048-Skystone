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

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleNew", group="Teleop")

//@Disabled
public class TeleNew extends OpMode {

    Bot bot = new Bot();
    int TankDrive = -1;
    /*
    fr = 1
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

        GameDrive drive = new GameDrive(bot);
        double leftStickY = (double) -gamepad1.left_stick_y;
        double rightTrigger = (double) gamepad1.right_trigger;
        double leftTrigger = (double) gamepad1.left_trigger;
        double rightStickX = (double) gamepad1.right_stick_x;
        double rightStickY = (double) -gamepad1.right_stick_y;
        drive.driveBot(leftStickY, rightStickX, rightTrigger, leftTrigger, 0.0, 0.0);


    }

    @Override
    public void stop() {
        bot.stop();

    }
}