package transformer;

import model.AbstractModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mykola on 07.09.2017.
 */
public class UniversalTransformer<T extends AbstractModel> {

    public T fromResultSetToObject(ResultSet rs, Class<T> classT) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        T result = classT.newInstance();
        Field[] fields;

        if (rs.next()) {

            Class cls = result.getClass();
            fields = cls.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                if ((field.getType().getName()).equals("java.lang.String")) {
                    field.set(cls, rs.getInt(field.getName()));
                } else if ((field.getType().getName()).equals("int")
                        || (field.getType().getName()).equals("java.lang.Integer")) {
                    field.setInt(cls, rs.getInt(field.getName()));
                }
            }
        }
        return result;
    }}