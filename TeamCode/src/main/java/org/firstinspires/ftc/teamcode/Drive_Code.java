/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Meccanum Drive Code")
@Disabled

public class Drive_Code extends OpMode {



    private DcMotor frontRight, frontLeft, backLeft, backRight;
    private DcMotorEx spinner, lift, intake, turnTable;



    public void init(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontRight  = hardwareMap.get(DcMotor.class, "Q1/fr");
        frontLeft = hardwareMap.get(DcMotor.class, "Q2/fl");
        backLeft  = hardwareMap.get(DcMotor.class, "Q3/bl");
        backRight = hardwareMap.get(DcMotor.class, "Q4/br");
        spinner = hardwareMap.get(DcMotorEx.class, "carousel");
        lift = hardwareMap.get(DcMotorEx.class, "lift");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        turnTable = hardwareMap.get(DcMotorEx.class, "turnTable");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Active");
        telemetry.update();
        setVelos();

        if(gamepad2.a){
            intake.setPower(1);
        }else if(gamepad2.b){
            intake.setPower(-1);
        }else{
            intake.setPower(0);
        }

        if(gamepad2.x){
            turnTable.setPower(1);
        }else if(gamepad2.y){
            turnTable.setPower(-1);
        }else{
            intake.setPower(0);
        }

        if(gamepad2.left_trigger != 0){
            turnTable.setPower(1);
        }else if(gamepad2.right_trigger != 0){
            turnTable.setPower(-1);
        }else{
            intake.setPower(0);
        }

        if(gamepad2.right_bumper){
            spinner.setPower(1);
        }else{
            spinner.setPower(0);
        }


    }

    public void setVelos(){
        frontRight.setPower((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.right_stick_x) * 0.8);
        frontLeft.setPower((gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.right_stick_x) * 0.8);
        backLeft.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.right_stick_x) * 0.8);
        backRight.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.8);
    }


}