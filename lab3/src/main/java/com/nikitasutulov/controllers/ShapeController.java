package com.nikitasutulov.controllers;

import com.nikitasutulov.utilities.InputManager;
import com.nikitasutulov.models.ShapeModel;
import com.nikitasutulov.views.ShapeView;

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
                default:
                    view.printMessage(ShapeView.INVALID_COMMAND);
                    break;
            }
        }
    }
}

