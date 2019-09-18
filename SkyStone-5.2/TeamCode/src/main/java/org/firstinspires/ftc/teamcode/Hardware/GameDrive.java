package org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.*;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GameDrive {

    public Bot bot;

    public GameDrive(Bot bot){
        this.bot = bot;
    }

    public static void Drive(double leftStickY, rightStickX,  rightTrigger,  leftTrigger,  leftStickX, rightStickY) {
        bot.drivePower(leftStickY);
        bot.turnPower(rightStickX);

        if (rightTrigger > 0.15 && leftTrigger < 0.15)
            bot.strafePower(rightTrigger);
        else if (leftTrigger > 0.15 && rightTrigger < 0.15)
            bot.strafePower(-leftTrigger);
        else
            bot.strafePower(0);
}