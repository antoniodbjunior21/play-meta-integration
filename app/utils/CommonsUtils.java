package utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AgiumSoft on 08/02/2017.
 */
public class CommonsUtils {

    public static void limparColecao(List<?> lista) {
        try{

            if(lista == null){
                return;
            }

            Iterator<?> iterator = lista.iterator();
            Object baseEntidade=null;
            while (iterator.hasNext()) {
                baseEntidade = iterator.next();

                if (baseEntidade == null ) {
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void limparColecao(List<?> lista, String... fields) {
        try{
            Iterator<?> iterator = lista.iterator();
            Object baseEntidade=null;
            Field field=null;
            while (iterator.hasNext()) {
                baseEntidade = iterator.next();

                if (baseEntidade == null){
                    iterator.remove();
                    continue;
                }

                for(String fieldName : fields){

                    field = baseEntidade.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (field.get(baseEntidade)== null
                            || (field.get(baseEntidade) instanceof String
                                    && StringUtils.isEmpthOrNull(field.get(baseEntidade).toString()))){
                        iterator.remove();
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
