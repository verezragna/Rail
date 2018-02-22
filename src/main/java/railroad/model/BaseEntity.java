package railroad.model;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.StringJoiner;

@MappedSuperclass
public abstract class BaseEntity<T> {

    public abstract T getId();

    public abstract void setId(T t);

    @Override
    public String toString() {
        Class child = this.getClass();
        StringJoiner stringJoiner = new StringJoiner(",", child.getSimpleName() + "(", ")");
        Field[] fields = child.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                stringJoiner.add(field.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return stringJoiner.toString();
    }
}
