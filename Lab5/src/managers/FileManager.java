package managers;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.sun.source.tree.Tree;
import data.SpaceMarine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;



public class FileManager {
    private Gson gson = new Gson();
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeCollection(TreeMap<Integer, SpaceMarine> collection) {
        try (PrintWriter pw = new PrintWriter(new File(this.fileName))){
            pw.write(gson.toJson(collection));
            System.out.println("Коллекция успешна сохранена в файл!");
        } catch (Exception e) {

        }
    }

    public TreeMap<Integer, SpaceMarine> readCollection() {
        try (Scanner fileScanner = new Scanner(new File(this.fileName))){
            TreeMap<Integer, SpaceMarine> collection;
            Type collectionType = new TypeToken<TreeMap<Integer, SpaceMarine>>() {}.getType();
            collection = gson.fromJson(fileScanner.nextLine().trim(), collectionType);
            System.out.println("Коллекция успешна загружена!");
            return collection;
        } catch (FileNotFoundException e) {
            System.out.println("Загрузочный файл не найден!");
        } catch (NoSuchElementException e) {
            System.out.println("Загрузочный файл пуст!");
        } catch (JsonParseException | NullPointerException exception) {
            System.out.println("В загрузочном файле не обнаружена необходимая коллекция!");
        }
        return new TreeMap<>();
    }
}
