package Inventory;

import decoration.Decoration;
import hint.Hint;
import room.Room;

import java.util.List;

public record Inventory(List<Room> rooms, List<Decoration> decorations, List<Hint> hints) {
    
}
