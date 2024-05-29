package Model;

import java.util.ArrayList;

public interface IDao<E,I> {
        public int add(E bean);
        public int delete(String id);
        public int update(E bean);
    public ArrayList<E> findAll(E bean);

}