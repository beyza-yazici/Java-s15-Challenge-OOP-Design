package repository;

import models.Reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReaderRepository {
    private Map<Integer, Reader> readerStorage = new HashMap<>();

    public void save(Reader reader){
        readerStorage.put(reader.getId(), reader);
        System.out.println("Reader saved: " + reader);
    }

    public void delete(Reader reader){
        readerStorage.remove(reader.getId());
        System.out.println("Reader deleted: " + reader);
    }

    public Reader findReaderById(int id){
        return readerStorage.get(id);
    }

    public List<Reader> findAll(){
        return readerStorage.values().stream().collect(Collectors.toList());
    }

}
