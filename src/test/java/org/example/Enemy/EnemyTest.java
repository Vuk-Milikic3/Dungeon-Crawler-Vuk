package org.example.Enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    void basicEnemy_should_have_correct_initial_values() {
        Enemy enemy = new BasicEnemy("Test-Ninja", 100, 15);
        assertEquals("Test-Ninja", enemy.getName());
        assertEquals(100, enemy.getCurrentHealth());
        assertEquals(100, enemy.getMaxHealth());
        assertEquals(15, enemy.getDamage());
        assertTrue(enemy.isAlive());
    }

    @Test
    void eliteEnemy_should_have_correct_initial_values() {
        Enemy enemy = new EliteEnemy("Elite-Ninja", 150, 25);
        assertEquals("Elite-Ninja", enemy.getName());
        assertEquals(150, enemy.getCurrentHealth());
        assertEquals(150, enemy.getMaxHealth());
        assertEquals(25, enemy.getDamage());
        assertTrue(enemy.isAlive());
    }

    @Test
    void takeDamage_should_reduce_health() {
        Enemy enemy = new BasicEnemy("Test", 100, 10);
        enemy.takeDamage(30);
        assertEquals(70, enemy.getCurrentHealth());
        assertTrue(enemy.isAlive());
    }

    @Test
    void takeDamage_should_not_go_below_zero() {
        Enemy enemy = new BasicEnemy("Test", 50, 10);
        enemy.takeDamage(100);
        assertEquals(0, enemy.getCurrentHealth());
        assertFalse(enemy.isAlive());
    }

    @Test
    void takeDamage_should_do_nothing_when_amount_is_non_positive() {
        Enemy enemy = new BasicEnemy("Test", 100, 10);
        enemy.takeDamage(0);
        assertEquals(100, enemy.getCurrentHealth());
        enemy.takeDamage(-10);
        assertEquals(100, enemy.getCurrentHealth());
    }

    @Test
    void isAlive_should_return_false_when_health_is_zero() {
        Enemy enemy = new BasicEnemy("Test", 50, 10);
        enemy.takeDamage(50);
        assertFalse(enemy.isAlive());
    }

    @Test
    void toString_should_show_health_bar() {
        Enemy enemy = new BasicEnemy("Ninja", 80, 12);
        String result = enemy.toString();
        assertTrue(result.contains("Ninja"));
        assertTrue(result.contains("80/80 HP"));
    }
}
