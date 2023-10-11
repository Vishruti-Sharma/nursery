package com.amdocs.nursery.main;

import com.amdocs.nursery.dao.*;
import com.amdocs.nursery.model.*;
import com.amdocs.nursery.plantException.*;

import java.util.*;
import java.sql.*;


public class plantSearchMain {
	
	private static void addPlant() {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		//System.out.println("Add new plant id");
		  //int plantId=sc.nextInt();
		try {
			int plantId=0;
		  System.out.println("Add new plant name");
		  String plantName=sc.next();
		  System.out.println("Add new plant origin country name");
		  String originCountryName=sc.next();
		  System.out.println("Add new plant sunlight requirement(true/false)");
		  boolean sunlightRequired=sc.nextBoolean();
		  System.out.println("Add new plant water supply frequency(daily/weekly/alternateDays)");
		  String waterSupplyFrequency=sc.next();
		  System.out.println("Add new plant type");
		  String plantType=sc.next();
		  System.out.println("Add new plant cost");
		  double cost=sc.nextDouble();
		  
		 
		  
		  Plant p=new Plant(plantId,plantName,originCountryName,sunlightRequired,waterSupplyFrequency,plantType,cost);
		  dao.addPlant(p);
		  System.out.println("Plant Added Successfullyy");
		}
		catch(Exception e) {
			System.out.println("enter correct values\n");
//			try {
//				//sc.next();
//				throw new plantInputException("Please enter the String");
//			}catch(plantInputException e1) {
//				
//			}
		}
	}
	
	private static void deletePlant() {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the plantID of the plant to be deleted:");
		  int plantId=sc.nextInt();
		 
		int temp = dao.deletePlant(plantId);
		if(temp!=0) {
			//delete operation 
			System.out.println("Deletion Operation successful");
		}else {
			//delete operation failed
			try {
				throw new deleteException("delete operation failed plant ID not found\n");
			}catch(deleteException e) {
			 System.out.println(e);
			}
		}
		  
	}
	
	private static void updatePlantCost() throws plantInputException {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Plant ID for the plant whose details have to be updated:");
		int plantId = sc.nextInt();
		System.out.println("Enter the updated plant cost:");
		double updatedCost = sc.nextDouble();
		
		dao.updatePlantCost(plantId, updatedCost);
	}
	
	private static void showAllPlants() {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		List <Plant> plist=new ArrayList<Plant>();
		plist=dao.showAllPlants();
		System.out.println(plist);

	}
	
	private static void searchByOriginCountryName() throws plantInputException {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		List <Plant> plist=new ArrayList<Plant>();
		System.out.println("Enter the country name for the plant whose details you want");
		String originCountryName = sc.next();
		plist=dao.searchByOriginCountryName(originCountryName);
		System.out.println(plist);
	}
	
	private static void searchOutdoorPlantsWithSunlight() {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		List <Plant> plist=new ArrayList<Plant>();
		plist=dao.searchOutdoorPlantsWithSunlight();
		System.out.println(plist);
	}
	
	private static void countPlantsByWaterSupplyFrequency() {
		plantDao dao=new plantDao();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter for what water supply frequency you want to count plants(daily/weekly/alternatDays)");
		String waterFrequency=sc.next();
		
		System.out.println(dao.countPlantsByWaterSupplyFrequency(waterFrequency));
	}
	
	public static void main(String args[])throws plantInputException {
		int choice=0;
		plantDao dao=new plantDao();
		System.out.println("Enter your choice:");
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println("1. Add new plant\n"
				+ "2. Delete plant\n"
				+ "3. Update plant cost\n"
				+ "4. View all plants\n"
				+ "5. Find plant by origin country name\n"
				+ "6. Find outdoor plants which requires sunlight\n"
				+ "7. Count plants by water supply frequency\n"
				+ "8. Exit\n"
				);
		choice =sc.nextInt();
		switch(choice) {
		  case 1:

			  addPlant();
			  
		    break;
		  case 2:
		    deletePlant();
			  
			  break;
		  case 3:
			    updatePlantCost();
		    break;
		  case 4:
			    showAllPlants();
		    break;
		  case 5:
			  searchByOriginCountryName();
			  break;
		  case 6:
			  searchOutdoorPlantsWithSunlight();
		    break;
		  case 7:
			  countPlantsByWaterSupplyFrequency();
		    break;
		  case 8:
			  return;
		  default:
			  System.out.println("Given number does not correspond to a feature! Please choose another number.");
		}
		}
	}
}
