package SqlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationsSqlUtil implements SqlUtil{

    /**
     * ���ݴ���Ĳ������ز�ѯ���
     * @param user
     * @return ���ز�ѯ���
     */
    public String query(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//��ȡclass
    	Class c = user.getClass();
    	//�ж��Ƿ����Table���͵�ע��
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//��ȡtable������
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	
    	//��ʼ����SQL��䣬Ϊ�˷�ֹû���������������һ�����ܼ�where
    	sqlBuffer.append("select * from").append(tablename);
    	//���һ������������������Ӧ����Ҫ��where��and�����
    	int counts = 0;
    	//��ȡ�������Ե��ֶ�
    	Field[] fArray = c.getDeclaredFields();
    	//�������е��ֶ�
    	for (Field field : fArray) {
    		if(!field.isAnnotationPresent(Column.class)) {
    			continue;
    		}
    		
    		//�õ��ֶ�����ע���ֵ����Column��ֵ
    		Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			//�õ��ֶ���
			String fieldName = field.getName();
			//ƴ��get��������ע���ֶε�һ����ĸ��д��
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
            //������int��String�ȣ����Զ���ΪObject��
			Object fieldValue = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				fieldValue = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//�õ����ֶ���Ӧ��ֵ�����ܼ���ƴ��sql
			//������ֶ�û��ֵʱ��˵����û�����ã�Ҫ����
			if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
				continue;
			}
			
			counts++;
    		//ֱ���������ȷ�����޶������������������Ϊһ����һ��where
    		if(counts==1) {
    			sqlBuffer.append(" Where ").append(fieldName);
    		}else {
    			//�����������Ϊһ��˵��Ҫ��and
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
     * ���ݴ���Ĳ������ز������
     * @param user
     * @return ���ز������
     */
    public String insert(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//��ȡclass
    	Class c = user.getClass();
    	//�ж��Ƿ����Table���͵�ע��
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//��ȡtable������
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	//��ʼ����SQL���
    	sqlBuffer.append("INSERT INTO '").append(tablename).append("'(");
    	//��ȡ�������Ե��ֶ�
    	Field[] fArray = c.getDeclaredFields();
    	
    	//��ӡ��������ֶ�
    	for(Field field : fArray) {
    		//�ж��Ƿ����Column���͵�ע�� 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
          //�õ���������ע���ֵ  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //�õ��ֶ��� 
            String filedName = field.getName();  
            //��ȡ��Ӧ�ֶε�getXXX()����   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//��ͬ���Ե�ֵ 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(user);  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
            //����ӡid,��Ϊid������
            if(filedName != "id") {
         	   sqlBuffer.append("`").append(filedName).append("`,");  
             }
    	}
    	//ɾ������Ķ���
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")VLAUES(");
    	
    	//�ٱ���һ�飬��ӡ���ֶζ�Ӧ��ֵ
    	Field[] fArray2 = c.getDeclaredFields();  
    	for (Field field : fArray2) {    
    		 //�ж��Ƿ����Column���͵�ע�� 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
            //�õ���������ע���ֵ  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //�õ��ֶ��� 
            String filedName = field.getName();  
            //��ȡ��Ӧ�ֶε�getXXX()����   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//��ͬ���Ե�ֵ 
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
    	//ɾ��������Ķ���
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")");
    	return sqlBuffer.toString();
    }

    /**
     * ���ݴ���Ĳ������ز������
     * @param users
     * @return ���ز������
     */
    public String insert(List<User> users) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	//��ȡclass
    	Class c = users.get(0).getClass();
    	//�ж��Ƿ����Table���͵�ע��
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	//��ȡtable������
    	Table t = (Table)c.getAnnotation(Table.class);
    	String tablename = t.value();
    	//��ʼ����SQL���
    	sqlBuffer.append("INSERT INTO '").append(tablename).append("'(");
    	//��ȡ�������Ե��ֶ�
    	Field[] fArray = c.getDeclaredFields();
    	
    	//��ӡ��������ֶ�
    	for(Field field : fArray) {
    		//�ж��Ƿ����Column���͵�ע�� 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
          //�õ���������ע���ֵ  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //�õ��ֶ��� 
            String filedName = field.getName();  
            //��ȡ��Ӧ�ֶε�getXXX()����   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//��ͬ���Ե�ֵ 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(users.get(0));  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
            //����ӡid,��Ϊid������
            if(filedName != "id") {
         	   sqlBuffer.append("`").append(filedName).append("`,");  
             }
    	}
    	//ɾ������Ķ���
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.append(")VLAUES(");
    	
    	//����list
    	for(int i=0;i<users.size();i++) {
    		Field[] Array2 = c.getDeclaredFields();  
        	for (Field field : Array2) {    
        		 //�ж��Ƿ����Column���͵�ע�� 
                if(!field.isAnnotationPresent(Column.class)){  
                    continue;  
                }  
                //�õ���������ע���ֵ  
                Column column = field.getAnnotation(Column.class);  
                String columnName = column.value();    
                //�õ��ֶ��� 
                String filedName = field.getName();  
                //��ȡ��Ӧ�ֶε�getXXX()����   
                String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                        + filedName.substring(1);  
        	    Object fieldValue = null;//��ͬ���Ե�ֵ 
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
    	
    	//ɾ�������listԪ�ض�ģ���
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
    	return sqlBuffer.toString();
    	
    }

    /**
     * ���ݴ���Ĳ�������ɾ����䣨ɾ��idΪuser.id�ļ�¼��
     * @param user
     * @return ����ɾ�����
     */
    public String delete(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
	    Class c = user.getClass();
	    //�жϱ��Ƿ�Ϊע��
	    if(!c.isAnnotationPresent(Table.class)) {
		    return null;
	    }
	    Table t = (Table)c.getAnnotation(Table.class);
	
	    //�õ�������
	    String tableName = t.value();
	    sqlBuffer.append("DELETE FROM `").append(tableName).append("` WHERE `id` = ").append(user.getId());
	    return sqlBuffer.toString(); 
    }
    
    /**
     * ���ݴ���Ĳ������ظ�����䣨��idΪuser.id�ļ�¼�������ֶθ��³�user�еĶ�Ӧֵ��
     * @param user
     * @return ���ظ������
     */
    public String update(User user) {
    	StringBuffer sqlBuffer = new StringBuffer();
    	Class c = user.getClass();
    	//�жϱ��Ƿ�Ϊע��
    	if(!c.isAnnotationPresent(Table.class)) {
    		return null;
    	}
    	Table t = (Table)c.getAnnotation(Table.class);
    	
    	//�õ�������
    	String tableName = t.value();
    	sqlBuffer.append("UPDATE `").append(tableName).append("` SET"); 
    	//����ӳ��õ���ı���
    	Field[] fArray = c.getDeclaredFields();  
    	for (Field field : fArray) {    
    		 //�ж��Ƿ����Column���͵�ע�� 
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
            //�õ���������ע���ֵ  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //�õ��ֶ��� 
            String filedName = field.getName();  
            //��ȡ��Ӧ�ֶε�getXXX()����   
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
    	    Object fieldValue = null;//��ͬ���Ե�ֵ 
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(user);  
            } catch (Exception e) {  
               e.printStackTrace();  
            }   
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
               continue;  
            } 
          //���¹�������
            if(filedName != "id") {
            	sqlBuffer.append("`").append(filedName).append("` ").append("=").append(" `").append((String)fieldValue).append("` ");  
            }
           
        }   
    	//���µ�id
    	sqlBuffer.append("WHERE `id` = " ).append(user.getId());  
    	return sqlBuffer.toString();
    }
}
