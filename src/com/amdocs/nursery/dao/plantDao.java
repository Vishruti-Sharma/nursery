package com.amdocs.nursery.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.nursery.model.*;
import com.amdocs.nursery.plantException.plantInputException;

public class plantDao {
	PreparedStatement pst;
	plantConnection pc=new plantConnection();
	Connection con=pc.getConnection();
	

	public int addPlant(Plant p) {
		int count=0;
		try {
			pst=con.prepareStatement("insert into plants values(plantId_seq.nextval,?,?,?,?,?,?)");
			
			//pst.setInt(1,p.getPlantId());
			pst.setString(1, p.getPlantName());
			pst.setString(2,p.getOriginCountryName());
			
			char isSun='o';
			if(p.isSunlightRequired()==true) {
				isSun='Y';
			}else {
				isSun='N';
			}
		
			//pst.setBoolean(4,p.isSunlightRequired());
			pst.setString(3,String.valueOf(isSun));
			pst.setString(4,p.getWaterSupplyFrequency());
			pst.setString(5,p.getPlantType());
			pst.setDouble(6,p.getCost());
			count = pst.executeUpdate();
			return count;
			
		}catch(Exception e) {
			System.out.println("error add"+e.getMessage());
			
		}
		
		return 0;
		
	}
	
	public  int deletePlant(int delPlantId) {
		int count=0;
		try {
			pst=con.prepareStatement("delete from plants where plantId=?");
			pst.setInt(1, delPlantId);
			count=pst.executeUpdate();
//			System.out.println("Deleted! ");
					
		}catch(Exception e) {
			System.out.println("error delete"+e.getMessage());
		}
		return count;
	}
	
	public boolean updatePlantCost(int plantId, double updatedCost) throws plantInputException {
		int count=0;
		try {
			pst=con.prepareStatement("update plants set pcost=? where plantId=?");
			pst.setDouble(1,updatedCost);
			pst.setInt(2, plantId);
			count=pst.executeUpdate();//rows update
			if(count==0) {
				throw new plantInputException("Plant not found");
			}
			System.out.println("Updated! ");
			
		}catch(Exception e) {
			System.out.println("error update"+e.getMessage());
			
		}
		return true;
		
	}
	
	public List<Plant> showAllPlants(){
		List<Plant> plist=new ArrayList<Plant>();
		try {
			pst=con.prepareStatement("select * from plants");
			ResultSet count=pst.executeQuery();
			while(count.next()) {
				int id=count.getInt(1);
				String name=count.getString(2);
				String country=count.getString(3);
				char sun=count.getString(4).charAt(0);
				boolean isSunlight;
				if(sun=='Y') {
					isSunlight=true;
				}
				else {
					isSunlight=false;
				}
				String waterSupplyFrequency=count.getString(5);
				String type=count.getString(6);
				double cost=count.getDouble(7);
				Plant p=new Plant(id,name,country,isSunlight,waterSupplyFrequency,type,cost);
				plist.add(p);
			}
		}catch(Exception e) {
			System.out.println("error showallplants"+e.getMessage());
			
		}
		return plist;
		
	}
	
	public List<Plant> searchByOriginCountryName(String CountryName) throws plantInputException {
		List<Plant> plist=new ArrayList<Plant>();
		try {
			pst=con.prepareStatement("select * from plants where originCountryName=?");
			pst.setString(1, CountryName);
			ResultSet rs=pst.executeQuery();
			if(rs.next()==false) {
				throw new plantInputException("country name not found");
			}
			while(rs.next()) {
				int id=rs.getInt(1);
				String plantName=rs.getString(2);
				String originCountryName=rs.getString(3);
				
				char sunlight=rs.getString(4).charAt(0);
				boolean isSunlight;
				if(sunlight=='t') {
					isSunlight=true;
				}
				else {
					isSunlight=false;
				}
				
				String waterSupplyFrequency=rs.getString(5);
				String plantType=rs.getString(6);
				double pcost=rs.getDouble(7);
				
				Plant p=new Plant(id,plantName,originCountryName,isSunlight,waterSupplyFrequency,plantType,pcost);
				
				plist.add(p);
				
				
			}
			}
		catch(Exception e) {
			System.out.println("error origin country name "+e.getMessage());
		}
		
		return plist;
		
	}
	
	public List<Plant> searchOutdoorPlantsWithSunlight(){
		
		List<Plant> plist=new ArrayList<Plant>();
		try {
			pst=con.prepareStatement("select * from plants where plantType='outdoor' and sunlightRequired='true'");
			
			ResultSet count=pst.executeQuery();
			while(count.next()) {
				int id=count.getInt(1);
				String name=count.getString(2);
				String country=count.getString(3);
				char sun=count.getString(4).charAt(0);
				boolean isSunlight;
				if(sun=='Y') {
					isSunlight=true;
				}
				else {
					isSunlight=false;
				}
				String waterSupplyFrequency=count.getString(5);
				String type=count.getString(6);
				double cost=count.getDouble(7);
				Plant p=new Plant(id,name,country,isSunlight,waterSupplyFrequency,type,cost);
				plist.add(p);
			}
		}catch(Exception e) {
			System.out.println("error showallplants"+e.getMessage());
			
		}
		return plist;
		
	}
	
	public int countPlantsByWaterSupplyFrequency(String plantWater) {
		int count=0;
		try {
			pst=con.prepareStatement("select count(*) from plants where waterSupplyFrequency=?");
			pst.setString(1, plantWater);
			ResultSet rs=pst.executeQuery();
			
			rs.next();
			count = rs.getInt(1);
			System.out.println("Counted! ");
					
		}catch(Exception e) {
			System.out.println("error count"+e.getMessage());
		}
		return count;
		
	}
	
	
	
	
	
	
	
}
