package com.nikitasutulov.controllers;

import com.nikitasutulov.utilities.DatasetReader;
import com.nikitasutulov.utilities.DatasetWriter;
import com.nikitasutulov.utilities.InputManager;
import com.nikitasutulov.models.ShapeModel;
import com.nikitasutulov.views.ShapeView;

import java.io.IOException;

public class ShapeController {
    private final ShapeModel model;
    private final ShapeView view;

    public ShapeController() {
        model = new ShapeModel();
        view = new ShapeView();
    }

    public void start() {
        InputManager inputManager = new InputManager();
        while (true) {
            String command = inputManager.getCommandFromInput();
            String fileName;
            switch (command) {
                case InputManager.EXIT:
                    view.printMessage(ShapeView.EXITING);
                    return;
                case InputManager.HELP:
                    view.printMessage(ShapeView.HELP);
                    break;
                case InputManager.PRINT_COMMAND:
                    view.printDatasetWithMessage(ShapeView.SHOW_DATASET, model.getDataset());
                    break;
                case InputManager.SUM_COMMAND_START + InputManager.ALL:
                    view.printNumberWithMessage(ShapeView.AREA_SUM_ALL, model.calculateAllShapesAreaSum());
                    break;
                case InputManager.SUM_COMMAND_START + InputManager.TRIANGLES:
                    view.printNumberWithMessage(ShapeView.AREA_SUM_CERTAIN + ShapeView.TRIANGLES, model.calculateTrianglesAreaSum());
                    break;
                case InputManager.SUM_COMMAND_START + InputManager.RECTANGLES:
                    view.printNumberWithMessage(ShapeView.AREA_SUM_CERTAIN + ShapeView.RECTANGLES, model.calculateRectanglesAreaSum());
                    break;
                case InputManager.SUM_COMMAND_START + InputManager.CIRCLES:
                    view.printNumberWithMessage(ShapeView.AREA_SUM_CERTAIN + ShapeView.CIRCLES, model.calculateCirclesAreaSum());
                    break;
                case InputManager.SORT_COMMAND_START + InputManager.AREA:
                    view.printDatasetWithMessage(ShapeView.SORT_AREA, model.getDatasetSortedByArea());
                    break;
                case InputManager.SORT_COMMAND_START + InputManager.COLOR:
                    view.printDatasetWithMessage(ShapeView.SORT_COLOR, model.getDatasetSortedByColor());
                    break;
                case InputManager.SAVE:
                    fileName = inputManager.getFileNameFromInput(ShapeView.SAVE_FILE_QUERY);
                    view.printMessage(String.format(ShapeView.SAVING_TO_FORM, fileName));
                    DatasetWriter datasetWriter = new DatasetWriter();
                    try {
                        datasetWriter.writeDatasetToFile(model.getDataset(), fileName);
                    } catch (IOException e) {
                        throw new RuntimeException("Problem while saving dataset to file: " + e.getMessage());
                    }
                    view.printMessage(ShapeView.SAVED);
                    break;
                case InputManager.LOAD:
                    fileName = inputManager.getFileNameFromInput(ShapeView.LOAD_FILE_QUERY);
                    view.printMessage(String.format(ShapeView.LOADING_FROM_FORM, fileName));
                    DatasetReader datasetReader = new DatasetReader();
                    try {
                        model.setDataset(datasetReader.readDatasetFromFile(fileName));
                    } catch (Exception e) {
                        throw new RuntimeException("Problem while loading dataset from file: " + e.getMessage());
                    }
                    view.printMessage(ShapeView.LOADED);
                    break;
                default:
                    view.printMessage(ShapeView.INVALID_COMMAND);
                    break;
            }
        }
    }
}

