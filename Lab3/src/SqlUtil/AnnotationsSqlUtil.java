package SqlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationsSqlUtil implements SqlUtil{

    /**
     * 根据传入的参数返回查询语句
     * @param user
     * @return 返回查询语句
     */
    public String query(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//获取class
    	Class c = user.getClass();
    	//判断是否包含Table类型的注解
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//获取table的名字
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	
    	//开始构造SQL语句，为了防止没有条件的情况，这一步不能加where
    	sqlBuffer.append("select * from").append(tablename);
    	//添加一个计数器，这样方便应对需要加where，and的情况
    	int counts = 0;
    	//获取所有属性的字段
    	Field[] fArray = c.getDeclaredFields();
    	//遍历所有的字段
    	for (Field field : fArray) {
    		if(!field.isAnnotationPresent(Column.class)) {
    			continue;
    		}
    		
    		//拿到字段上面注解的值，即Column的值
    		Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			//拿到字段名
			String fieldName = field.getName();
			//拼接get方法名，注意字段第一个字母大写。
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
            //属性有int、String等，所以定义为Object类
			Object fieldValue = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				fieldValue = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//得到了字段相应的值，就能继续拼接sql
			//当这个字段没有值时，说明还没有设置，要跳过
			if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
				continue;
			}
			
			counts++;
    		//直到这里才能确定有限定的条件，如果计数器为一，加一个where
    		if(counts==1) {
    			sqlBuffer.append(" Where ").append(fieldName);
    		}else {
    			//如果计数器不为一，说明要加and
    			sqlBuffer.append(" and ").append(fieldName);
    		}
    		
    		if(fieldValue instanceof String) {
    			sqlBuffer.append("=").append("'").append(fieldValue).append("'");
    		}else {
    			//fieldValue instanceof Integer
    			sqlBuffer.append("=").append(fieldValue);
    		}
    	}
    	return sqlBuffer.toString();		
    }

    /**
     * 根据传入的参数返回插入语句
     * @param user
     * @return 返回插入语句
     */
    public String insert(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//获取class
    	Class c = user.getClass();
    	//判断是否包含Table类型的注解
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//获取table的名字
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	//开始构造SQL语句
    	sqlBuffer.append("INSERT INTO '").append(tablename).append("'(");
    	//获取所有属性的字段
    	Field[] fArray = c.getDeclaredFields();
    	
    	//打印表的属性字段
    	for(Field field : fArray) {
    		//判断是否包含Column类型的注解 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
          //得到类型上面注解的值  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //拿到字段名 
            String filedName = field.getName();  
            //获取相应字段的getXXX()方法   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//不同属性的值 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(user);  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
            //不打印id,因为id自增的
            if(filedName != "id") {
         	   sqlBuffer.append("`").append(filedName).append("`,");  
             }
    	}
    	//删除多余的逗号
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")VLAUES(");
    	
    	//再遍历一遍，打印出字段对应的值
    	Field[] fArray2 = c.getDeclaredFields();  
    	for (Field field : fArray2) {    
    		 //判断是否包含Column类型的注解 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
            //得到类型上面注解的值  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //拿到字段名 
            String filedName = field.getName();  
            //获取相应字段的getXXX()方法   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//不同属性的值 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(user);  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
            if(filedName != "id") {
               if(fieldValue instanceof String) {
         	       sqlBuffer.append("`").append(fieldValue).append("`,");  
               }
               else {
            	   sqlBuffer.append(fieldValue).append(",");
               }
             }
        }
    	//删除最后多余的逗号
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")");
    	return sqlBuffer.toString();
    }

    /**
     * 根据传入的参数返回插入语句
     * @param users
     * @return 返回插入语句
     */
    public String insert(List<User> users) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//获取class
    	Class c = users.get(0).getClass();
    	//判断是否包含Table类型的注解
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//获取table的名字
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	//开始构造SQL语句
    	sqlBuffer.append("INSERT INTO '").append(tablename).append("'(");
    	//获取所有属性的字段
    	Field[] fArray = c.getDeclaredFields();
    	
    	//打印表的属性字段
    	for(Field field : fArray) {
    		//判断是否包含Column类型的注解 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
          //得到类型上面注解的值  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //拿到字段名 
            String filedName = field.getName();  
            //获取相应字段的getXXX()方法   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//不同属性的值 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(users.get(0));  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
            //不打印id,因为id自增的
            if(filedName != "id") {
         	   sqlBuffer.append("`").append(filedName).append("`,");  
             }
    	}
    	//删除多余的逗号
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")VLAUES(");
    	
    	//遍历list
    	for(int i=0;i<users.size();i++) {
    		Field[] Array2 = c.getDeclaredFields();  
        	for (Field field : Array2) {    
        		 //判断是否包含Column类型的注解 
                if(!field.isAnnotationPresent(Column.class)){  
                    continue;  
                }  
                //得到类型上面注解的值  
                Column column = field.getAnnotation(Column.class);  
                String columnName = column.value();    
                //拿到字段名 
                String filedName = field.getName();  
                //获取相应字段的getXXX()方法   
                String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                        + filedName.substring(1);  
        	    Object fieldValue = null;//不同属性的值 
                try {  
                    Method getMethod = c.getMethod(getMethodName);  
                    fieldValue = getMethod.invoke(users.get(i));  
                } catch (Exception e) {  
                   e.printStackTrace();  
                }   
                if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
                   continue;  
                } 
                if(filedName != "id") {
                   if(fieldValue instanceof String) {
             	       sqlBuffer.append("`").append(fieldValue).append("`,");  
                   }
                   else {
                	   sqlBuffer.append(fieldValue).append(",");
                   }
                 }
            }
        	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
        	sqlBuffer.append("),(");
    	}
    	
    	//删除掉最后list元素多的，（
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	return sqlBuffer.toString();
    	
    }

    /**
     * 根据传入的参数返回删除语句（删除id为user.id的记录）
     * @param user
     * @return 返回删除语句
     */
    public String delete(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
	    Class c = user.getClass();
	    //判断表是否为注解
	    if(!c.isAnnotationPresent(Table.class)) {
		    return null;
	    }
	    Table t = (Table)c.getAnnotation(Table.class);
	
	    //得到表名称
	    String tableName = t.value();
	    sqlBuffer.append("DELETE FROM `").append(tableName).append("` WHERE `id` = ").append(user.getId());
	    return sqlBuffer.toString(); 
    }
    
    /**
     * 根据传入的参数返回更新语句（将id为user.id的记录的其它字段更新成user中的对应值）
     * @param user
     * @return 返回更新语句
     */
    public String update(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	Class c = user.getClass();
    	//判断表是否为注解
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	Table t = (Table)c.getAnnotation(Table.class);
    	
    	//得到表名称
    	String tableName = t.value();
    	sqlBuffer.append("UPDATE `").append(tableName).append("` SET"); 
    	//利用映射得到类的变量
    	Field[] fArray = c.getDeclaredFields();  
    	for (Field field : fArray) {    
    		 //判断是否包含Column类型的注解 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
            //得到类型上面注解的值  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //拿到字段名 
            String filedName = field.getName();  
            //获取相应字段的getXXX()方法   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//不同属性的值 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(user);  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
          //更新过的属性
            if(filedName != "id") {
            	sqlBuffer.append("`").append(filedName).append("` ").append("=").append(" `").append((String)fieldValue).append("` ");  
            }
           
        }   
    	//更新的id
    	sqlBuffer.append("WHERE `id` = " ).append(user.getId());  
    	return sqlBuffer.toString();
    }
}
