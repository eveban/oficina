package br.com.angelellirh.converter;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Id;

/**
 * 
 * @author Victor Lindberg
 * 
 */
@FacesConverter(value="entityConverter")  
public class EntityConverter implements Converter {  
  
    @Override  
    public Object getAsObject(FacesContext ctx, UIComponent component,  
            String value) {  
        if (value != null) {  
            return component.getAttributes().get(value);  
        }  
        return null;  
    }  
  
    @Override  
    public String getAsString(FacesContext ctx, UIComponent component,  
            Object obj) {  
        if (obj != null && !"".equals(obj)) {  
            String id;  
            try {  
                id = this.getId(getClazz(ctx, component), obj);  
                if (id == null) {  
                    id = "";  
                }  
                id = id.trim();  
                component.getAttributes().put(id,  
                        getClazz(ctx, component).cast(obj));  
                return id;  
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (NoSuchFieldException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            } catch (ClassCastException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
  
    private Class<?> getClazz(FacesContext facesContext, UIComponent component) {  
        return component.getValueExpression("value").getType(  
                facesContext.getELContext());  
    }  
  
    public String getId(Class<?> clazz, Object obj) throws SecurityException,  
            NoSuchFieldException, IllegalArgumentException,  
            IllegalAccessException {  
        for (Field field : clazz.getDeclaredFields()) {  
            if ((field.getAnnotation(Id.class)) != null) {  
                Field privateField = clazz.getDeclaredField(field.getName());  
                privateField.setAccessible(true);  
                if (privateField.get(clazz.cast(obj)) != null) {  
                    return (String) field.getType()  
                            .cast(privateField.get(clazz.cast(obj))).toString();  
                } else {  
                    return null;  
                }  
            }  
        }  
        return null;  
    }  
}  