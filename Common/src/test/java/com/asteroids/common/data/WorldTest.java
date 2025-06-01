package com.asteroids.common.data;

import com.asteroids.common.gameObjects.Entity;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WorldTest {

    private World world = new World(1000, 1000, null);

    // Create and return a mocked Entity
    private Entity mockEntity(double x, double y, double width, double height) {
        Entity entity = Mockito.mock(Entity.class);
        when(entity.getColliderX()).thenReturn(x);
        when(entity.getColliderY()).thenReturn(y);
        when(entity.getColliderWidth()).thenReturn(width);
        when(entity.getColliderHeight()).thenReturn(height);
        return entity;
    }

    @Test
    @DisplayName("Should detect collision when entities overlap fully")
    void testIsColliding_fullOverlap() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(5, 5, 10, 10);
        assertTrue(world.isColliding(entityA, entityB), "Entities should be colliding with full overlap");
    }

    @Test
    @DisplayName("Should detect collision when entities touch on an edge (minimal overlap)")
    void testIsColliding_minimalOverlap() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(9, 9, 2, 2); // Overlaps with 1 pixel in X and Y
        assertTrue(world.isColliding(entityA, entityB), "Entities should be colliding with minimal overlap");
    }

    @Test
    @DisplayName("Should detect collision when one entity is contained within another")
    void testIsColliding_contained() {
        Entity entityA = mockEntity(0, 0, 20, 20);
        Entity entityB = mockEntity(5, 5, 5, 5);
        assertTrue(world.isColliding(entityA, entityB), "Entity B should be colliding as it's inside A");
    }

    @Test
    @DisplayName("Should not detect collision when entities are separated horizontally")
    void testIsColliding_separatedX() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(11, 0, 10, 10); // Separated by 1 pixel horizontally
        assertFalse(world.isColliding(entityA, entityB), "Entities should not be colliding horizontally separated");
    }

    @Test
    @DisplayName("Should not detect collision when entities are separated vertically")
    void testIsColliding_separatedY() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(0, 11, 10, 10); // Separated by 1 pixel vertically
        assertFalse(world.isColliding(entityA, entityB), "Entities should not be colliding vertically separated");
    }

    @Test
    @DisplayName("Should not detect collision when entities touch at edges but doesnt overlap (X-axis)")
    void testIsColliding_touchingEdgesX() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(10, 0, 10, 10); // Touch at x=10
        assertFalse(world.isColliding(entityA, entityB), "Entities touching at X-edges should not collide");
    }

    @Test
    @DisplayName("Should not detect collision when entities touch at edges but doesnt overlap (Y-axis)")
    void testIsColliding_touchingEdgesY() {
        Entity entityA = mockEntity(0, 0, 10, 10);
        Entity entityB = mockEntity(0, 10, 10, 10); // Touch at y=10
        assertFalse(world.isColliding(entityA, entityB), "Entities touching at Y-edges should not collide");
    }
}


