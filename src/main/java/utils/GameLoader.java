package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import game.*;
import game.event.Event;
import game.event.EventChoice;
import game.event.effect.*;
import game.event.effect.calculation.CalculationFixed;
import game.event.effect.calculation.CalculationPercentage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class GameLoader {
    private final XStream xstream;
    private String savePath;
    private LinkedList<File> saveFileList;

    public GameLoader() {
        xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(Season.class);
        xstream.processAnnotations(Event.class);
        xstream.processAnnotations(EventChoice.class);
        xstream.processAnnotations(EventEffectFactionSatisfaction.class);
        xstream.processAnnotations(EventEffectFactionSupporter.class);
        xstream.processAnnotations(EventEffectIndustrie.class);
        xstream.processAnnotations(EventEffectAgriculture.class);
        xstream.processAnnotations(EventEffectMoney.class);
        xstream.processAnnotations(EventEffectFood.class);
        xstream.processAnnotations(CalculationPercentage.class);
        xstream.processAnnotations(CalculationFixed.class);

        xstream.processAnnotations(Population.class);
        xstream.processAnnotations(Faction.class);
        xstream.processAnnotations(Island.class);
        xstream.processAnnotations(Ressource.class);
        saveFileList = new LinkedList<>();
        savePath = "./";
        loadSaveList();
    }

    private void loadSaveList() {
        saveFileList.clear();
        saveFileList.addAll(Arrays.asList(Objects.requireNonNull(new File(savePath).listFiles((dir, name) -> name.toLowerCase().endsWith(".sav")))));
    }

    public void createSaveFile(String fileName, Island island) throws IOException {
        File newFile = new File(savePath + fileName + ".sav");
        if (newFile.createNewFile()) {
            saveFileList.add(newFile);
        }
        saveGame(island, saveFileList.indexOf(newFile));
    }

    public void saveGame(Island island, int index) throws IOException {
        FileWriter fileWriter = new FileWriter(saveFileList.get(index));
        fileWriter.write(xstream.toXML(island));
        fileWriter.flush();
    }

    public String showSaveList() {
        loadSaveList();
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < saveFileList.size(); i++) {
            display.append(i).append(". ").append(saveFileList.get(i).getName().replaceFirst("[.][^.]+$", "")).append("\n");
        }
        return display.toString();
    }

    public Island loadGame(int index) throws IOException {
        String saveJson = Files.readString(Paths.get(saveFileList.get(index).getPath()), StandardCharsets.UTF_8);
        Island island = (Island) xstream.fromXML(saveJson);
        System.out.println(island.getRessources().getFood());
        return island;
    }

    public ArrayList<Season> loadSeasons() throws IOException {
        String saveJson = Files.readString(Paths.get(savePath + "/seasons.data"), StandardCharsets.UTF_8);
        return (ArrayList<Season>) xstream.fromXML(saveJson);
    }
}
