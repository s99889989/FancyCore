package com.daxton.fancycore.api.mysql;

import java.util.HashMap;
import java.util.Map;

public class MySqlComponent {
	//值列表
	Map<String, Object> keyValue;
	//主Key
	public String mainKey;
	//主Key值
	public String mainValue;
	//主Key大小
	public int mainKeySize;
	//表名
	public String table;

	private MySqlComponent(Map<String, Object> keyValue, String mainKey, String mainValue, int mainKeySize, String table) {
		this.keyValue = keyValue;
		this.mainKey = mainKey;
		this.mainValue = mainValue;
		this.mainKeySize = mainKeySize;
		this.table = table;
	}

	public MySqlComponent addKeyValue(String key, Object value){
		this.keyValue.put(key, value);
		return this;
	}

	public String getCreateString(){
		StringBuilder createString = new StringBuilder("Create Table If Not Exists " + table + " (" + mainKey + " VARCHAR(" + mainKeySize + ") Primary Key Not Null");

		for(String key : keyValue.keySet()){
			Object object = keyValue.get(key);
			if(object instanceof String){
				String value = (String) object;
				if(value.length() <= 255){
					createString.append(", ").append(key).append(" TINYTEXT Not Null");
					continue;
				}else if(value.length() <= 16383){
					createString.append(", ").append(key).append(" VARCHAR Not Null");
					continue;
				}else if(value.length() <= 65535){
					createString.append(", ").append(key).append(" TEXT Not Null");
					continue;
				}else if(value.length() <= 16777215){
					createString.append(", ").append(key).append(" MEDIUMTEXT Not Null");
					continue;
				}
			}
			if(object instanceof Boolean){
				createString.append(", ").append(key).append(" BOOLEAN Not Null");
				continue;
			}
			if(object instanceof Byte){
				createString.append(", ").append(key).append(" TINYINT Not Null");
				continue;
			}
			if(object instanceof Short){
				createString.append(", ").append(key).append(" SMALLINT Not Null");
				continue;
			}
			if(object instanceof Integer){
				createString.append(", ").append(key).append(" INTEGER Not Null");
				continue;
			}
			if(object instanceof Long){
				createString.append(", ").append(key).append(" BIGINT Not Null");
				continue;
			}
			if(object instanceof Float){
				createString.append(", ").append(key).append(" FLOAT Not Null");
				continue;
			}
			if(object instanceof Double){
				createString.append(", ").append(key).append(" DOUBLE Not Null");
			}
		}

		createString.append(")");
		return createString.toString();
	}

	public String getInsertString(){
		StringBuilder createString = new StringBuilder("Insert Into " + table + " (" + mainKey);

		for(String key : keyValue.keySet()){
			createString.append(", ");
			Object object = keyValue.get(key);
			if(object instanceof String){
				String value = (String) object;
				if(value.length() <= 255){
					createString.append(key);
					continue;
				}else if(value.length() <= 16383){
					createString.append(key);
					continue;
				}else if(value.length() <= 65535){
					createString.append(key);
					continue;
				}else if(value.length() <= 16777215){
					createString.append(key);
					continue;
				}
			}
			if(object instanceof Boolean){
				createString.append(key);
				continue;
			}
			if(object instanceof Byte){
				createString.append(key);
				continue;
			}
			if(object instanceof Short){
				createString.append(key);
				continue;
			}
			if(object instanceof Integer){
				createString.append(key);
				continue;
			}
			if(object instanceof Long){
				createString.append(key);
				continue;
			}
			if(object instanceof Float){
				createString.append(key);
				continue;
			}
			if(object instanceof Double){
				createString.append(key);
			}
		}
		createString.append(") Values (").append(mainValue);

		for(String key : keyValue.keySet()){
			createString.append(", ");
			Object object = keyValue.get(key);
			if(object instanceof String){
				String value = (String) object;
				if(value.length() <= 255){
					createString.append(value);
					continue;
				}else if(value.length() <= 16383){
					createString.append(value);
					continue;
				}else if(value.length() <= 65535){
					createString.append(value);
					continue;
				}else if(value.length() <= 16777215){
					createString.append(value);
					continue;
				}
			}
			if(object instanceof Boolean){
				boolean value = (Boolean) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Byte){
				byte value = (Byte) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Short){
				short value = (Short) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Integer){
				int value = (Integer) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Long){
				long value = (Long) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Float){
				float value = (Float) object;
				createString.append(value);
				continue;
			}
			if(object instanceof Double){
				double value = (Double) object;
				createString.append(value);
			}
		}

		createString.append(") On Duplicate Key Update ").append(mainKey).append("=").append(mainValue);

		return createString.toString();
	}

	public String getUpdateString(){
		StringBuilder createString = new StringBuilder("Update " + table + " Set ");
		int count = 0;
		for(String key : keyValue.keySet()){
			count++;
			if(count > 1){
				createString.append(", ");
			}
			Object object = keyValue.get(key);
			if(object instanceof String){
				String value = (String) object;
				if(value.length() <= 255){
					createString.append(key).append("=").append(value);
					continue;
				}else if(value.length() <= 16383){
					createString.append(key).append("=").append(value);
					continue;
				}else if(value.length() <= 65535){
					createString.append(key).append("=").append(value);
					continue;
				}else if(value.length() <= 16777215){
					createString.append(key).append("=").append(value);
					continue;
				}
			}
			if(object instanceof Boolean){
				boolean value = (Boolean) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Byte){
				byte value = (Byte) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Short){
				short value = (Short) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Integer){
				int value = (Integer) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Long){
				long value = (Long) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Float){
				float value = (Float) object;
				createString.append(key).append("=").append(value);
				continue;
			}
			if(object instanceof Double){
				double value = (Double) object;
				createString.append(key).append("=").append(value);
			}
		}
		createString.append(" Where ").append(mainKey).append("=").append(mainValue);
		return createString.toString();
	}

	public static class MySqlComponentBuilder{
		//值列表
		private Map<String, Object> keyValue = new HashMap<>();
		//主Key
		private String mainKey = "fancy_key";
		//主Key值
		private String mainValue = "123456";
		//主Key大小
		private int mainKeySize = 255;
		//表名
		private String table = "fancy_table";

		public static MySqlComponentBuilder getInstance(){
			return new MySqlComponentBuilder();
		}

		public MySqlComponentBuilder addKeyValue(String key, Object value){
			this.keyValue.put(key, value);
			return this;
		}

		public MySqlComponentBuilder setKeyValue(Map<String, Object> keyValue) {
			this.keyValue = keyValue;
			return this;
		}

		public MySqlComponentBuilder setMainKey(String mainKey) {
			this.mainKey = mainKey;
			return this;
		}

		public MySqlComponentBuilder setMainValue(String mainValue) {
			if(mainValue.length() < this.mainKeySize){
				this.mainValue = mainValue;
			}
			return this;
		}

		public MySqlComponentBuilder setMainKeySize(int mainKeySize) {
			if(mainKeySize <= 768){
				this.mainKeySize = mainKeySize;
			}
			return this;
		}

		public MySqlComponentBuilder setTable(String table) {
			this.table = table;
			return this;
		}

		public MySqlComponent build(){
			return new MySqlComponent(keyValue, mainKey, mainValue, mainKeySize, table);
		}

	}

}
