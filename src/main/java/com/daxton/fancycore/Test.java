package com.daxton.fancycore;

public class Test {

	public static void main(String[] args){

		double rotX = 0;
		double rotY = 90;
		double rotZ = 0;

		//繞X軸旋轉
		double cosX = Math.cos(Math.toRadians(rotX));
		double sinX = Math.sin(Math.toRadians(rotX));
		//繞Y軸旋轉
		double cosY = Math.cos(Math.toRadians(rotY));
		double sinY = Math.sin(Math.toRadians(rotY));
		//繞Z軸旋轉
		double cosZ = Math.cos(Math.toRadians(rotZ));
		double sinZ = Math.sin(Math.toRadians(rotZ));
		System.out.println(cosX+" : "+sinX+" : "+cosZ+" : "+sinZ);
		double nX = 10;
		double nY = 0;
		double nZ = 0;

		double r11 = cosY*cosZ - sinX*sinY*sinZ;
		double r13 = sinY*cosZ + sinX*cosY*sinZ;
		double r21 = cosY*sinZ + sinX*sinY*cosZ;
		double r23 = sinY*sinZ - sinX*cosY*cosZ;

		double rX = r11*nX - cosX*sinZ*nY + r13*nZ;
		double rY = r21*nX + cosX*cosZ*nY + r23*nZ;
		double rZ = -cosX*sinY*nX + sinX*nY + cosX*cosY*nZ;
//		if(rX < 0){
//			rX = 0;
//		}
//		if(rZ < 0){
//			rZ = 0;
//		}
		System.out.println("X: "+rX+" Z:"+rZ);

	}

}
