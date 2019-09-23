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

//import org.firstinspires.ftc.teamcode.TensorFlowStuff.TensorFlow;

public class Bot {
    public static DcMotor BL, BR, FL, FR, lift;
//            , hook, lift, joint;
//    public CRServo inBOBO;
    public Servo claw;
//    public DigitalChannel liftLimit, hookLimit;
//    public RevBlinkinLedDriver blinkin;
//    int originTick;
    HardwareMap map;
    Telemetry tele;
//    TensorFlow tensorFlow;

    Double powerModifier = 0.02;
    double turnSpeed = 0.25;
    final double proportionalValue = 0.000005;

    //double error = 180 - gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    //Double turnSpeed = 0.5;
    //Integer angle = -45;
//    public static BNO055IMU gyro;
//    BNO055IMU.Parameters parameters;
//    Orientation angles;

    public Bot() {
    }

    public void init(HardwareMap map, Telemetry tele, boolean auton) {
        this.map = map;
        this.tele = tele;

//        hook = this.map.get(DcMotor.class, "hook");
        BR = this.map.get(DcMotor.class, "BR");
        BL = this.map.get(DcMotor.class, "BL");
        FL = this.map.get(DcMotor.class, "FL");
        FR = this.map.get(DcMotor.class, "FR");
        lift = this.map.get(DcMotor.class, "lift");
        claw = this.map.get(Servo.class, "claw");
//        inBOBO = this.map.get(CRServo.class,"Bintake");
//        joint = this.map.get(DcMotor.class, "joint");

//        door = this.map.get(Servo.class, "door");
        //  blinkin = this.map.get(RevBlinkinLedDriver.class, "rgbReady");

        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
//        joint.setDirection(DcMotorSimple.Direction.FORWARD);
//        inBOBO.setDirection(DcMotorSimple.Direction.FORWARD);
//        hook.setDirection(DcMotorSimple.Direction.REVERSE);

        this.changeRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        gyro = this.map.get(BNO055IMU.class, "gyro");
//        gyro.initialize(parameters);
//        tele.addData(">", "Gyro Calibrating. Do Not Move!");
//        tele.update();

//        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        joint.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        hook.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public static void changeRunMode(DcMotor.RunMode runMode) {
        BL.setMode(runMode);
        BR.setMode(runMode);
        FL.setMode(runMode);
        FR.setMode(runMode);
//        hook.setMode(runMode);
        lift.setMode(runMode);
//        joint.setMode(runMode);
        //  intake.setMode(runMode);
    }

    public void drive(double in) {
        BL.setPower(in);
        BR.setPower(in);
        FR.setPower(in);
        FL.setPower(in);
    }

    public void stop(){
        this.drive(0.0);
    }

    public int FLcurPos() {
        return FL.getCurrentPosition();
    }

    public int FRcurPos() {
        return FR.getCurrentPosition();
    }

    public int BLcurPos() {
        return BL.getCurrentPosition();
    }

    public int BRcurPos() {
        return BR.getCurrentPosition();
    }

    public void getPos() {
        FLcurPos();
        FRcurPos();
        BLcurPos();
        BRcurPos();
    }

    public void getDrivePosition() {
        FL.getCurrentPosition();
        FR.getCurrentPosition();
        BL.getCurrentPosition();
        BR.getCurrentPosition();
    }
    public void turnPower(double power) {
        BL.setPower(-power);
        BR.setPower(power);
        FR.setPower(-power);
        FL.setPower(power);
    }

    public void drivePower(double power) {
        FL.setPower(power);
        FR.setPower(power);
        BL.setPower(-power);
        BR.setPower(-power);
    }
    public void strafePower(double power) {
        FL.setPower(power);
        FR.setPower(power);
        BL.setPower(power);
        BR.setPower(power);
    }
    public void setPower(double power) {
        strafePower(power);
    }


    public void PID() {
        if (Math.abs(FL.getCurrentPosition() / 1000) <= FL.getTargetPosition()) {setPower(1);}
        else if ((Math.abs(FL.getCurrentPosition() / 800) <= FL.getTargetPosition())) {setPower(0.8);}
        else if ((Math.abs(FL.getCurrentPosition() / 650) <= FL.getTargetPosition())) {setPower(0.65);}
        else if ((Math.abs(FL.getCurrentPosition() / 500) <= FL.getTargetPosition())) {setPower(0.5);}
        else if ((Math.abs(FL.getCurrentPosition() / 450) <= FL.getTargetPosition())) {setPower(0.45);}
        else if ((Math.abs(FL.getCurrentPosition() / 200) <= FL.getTargetPosition())) {setPower(0.2);}
        else if ((Math.abs(FL.getCurrentPosition()) >= FL.getTargetPosition())) {setPower(0);}
    }

    public double motorSpeed() {
        if (Math.abs(FL.getCurrentPosition()) < Math.abs(FL.getTargetPosition())) {
        } return Math.abs(FL.getTargetPosition()) - Math.abs(FL.getCurrentPosition() * proportionalValue);
    }

}