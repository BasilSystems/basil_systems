package com.basilsystems.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class DataProvider {

	public static List<PlacesModel> places = new ArrayList<PlacesModel>();
	public static HashMap<String, List<DeviceModel>> devices = new HashMap<String, List<DeviceModel>>();
	public static HashMap<String, List<ApplianceModel>> appliances = new HashMap<String, List<ApplianceModel>>();

	//Returns the entire XML document
	private static Document getDocument(InputStream inputStream) {
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(inputStream);
			document = db.parse(inputSource);
		} catch (ParserConfigurationException e) {
			return null;
		} catch (SAXException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return document;
	}

	public static List<PlacesModel> getPlaces(InputStream placesFile) {
		if(placesFile != null){
			Document document = getDocument(placesFile);
			NodeList placesList = document.getElementsByTagName("Place");
			for(int i=0 ; i<placesList.getLength() ; i++){
				Element item = (Element)placesList.item(i);
				String placeName = item.getTextContent();
				Node id_node = item.getAttributes().getNamedItem("id");
				if(id_node != null){
					String placeId = id_node.getTextContent();
					places.add(new PlacesModel(placeId, placeName));
				}
			}

		}
		return places;
	}

	public static List<DeviceModel> getDeviceFromFile(InputStream devicesFile, String place) {
		if(devicesFile != null){
			Document document = getDocument(devicesFile);
			NodeList placesList = document.getElementsByTagName("Device");
			for(int i=0 ; i<placesList.getLength() ; i++){
				Element item = (Element)placesList.item(i);
				String deviceName = item.getTextContent();
				Node id_node = item.getAttributes().getNamedItem("id");
				if(id_node != null){
					String deviceId = id_node.getTextContent();
					if(devices.get(place) == null){
						devices.put(place, new ArrayList<DeviceModel>());
					}
					devices.get(place).add(new DeviceModel(deviceId, deviceName));

				}

			}
			return devices.get(place);
		}
		return null;
	}

	public static List<ApplianceModel> getAppliancesFromFile( String deviceKey, InputStream appliancesFile) {
		if(appliancesFile != null){
			Document document = getDocument(appliancesFile);
			NodeList placesList = document.getElementsByTagName("Appliance");
			for(int i=0 ; i<placesList.getLength() ; i++){
				Element item = (Element)placesList.item(i);
				NamedNodeMap itemAttributes = item.getAttributes();
				String id = itemAttributes.getNamedItem("id").getTextContent();
				String name = itemAttributes.getNamedItem("name").getTextContent();
				String status = item.getElementsByTagName("Status").item(0).getTextContent();
				String sliding = item.getElementsByTagName("Sliding").item(0).getTextContent();
				String auto = item.getElementsByTagName("Auto").item(0).getTextContent();
				if(appliances.get(deviceKey) == null){
					appliances.put(deviceKey, new ArrayList<ApplianceModel>());
				}
				appliances.get(deviceKey).add(new ApplianceModel(deviceKey, name, Integer.parseInt(status), Boolean.parseBoolean(sliding), Boolean.parseBoolean(auto)));
			}
			if(deviceKey != null){
				return appliances.get(deviceKey);
			}
		}
		return null;
	}

}
