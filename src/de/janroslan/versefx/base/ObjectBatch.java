/*
 * Copyright 2016, Jan-Philipp Roslan, Alle Rechte vorbehalten
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javafx.scene.Node;
import org.dyn4j.dynamics.World;

/**
 * The ObjectBatch registers objects for drawing on the screen It serves as an
 * abstract object carrier which connects the levels and the LevelLoader to hide
 * sensitive attributes from the levels
 *
 * @author Jackjan
 */
public class ObjectBatch implements ObjectCollector {

    private final ArrayList<Node> batch;
    
    private final HashMap<String, HashMap<Collidable,String[]>> collisionDict;

    // This about the Manager (Level) is carried to inject it into gameobjecs
    private ObjectManager man;
    
    private World world;

    public ObjectBatch(ObjectManager man, World world) {
        batch = new ArrayList<>();
        this.man = man;
        this.world = world;
        collisionDict = new HashMap<>();
    }

    public void injectInfo(BasicNode o) {
        o.setManager(man);
    }

    @Override
    public void add(BasicNode... n) {
        for (BasicNode node : n) {
            injectInfo(node);
            batch.add(node.getNode());
        }
    }

    @Override
    public void registerCollider(Collidable o, CollisionType type, String... targets) {
        // Add new tag-set if not existent
        if (collisionDict.get(o.getTag()) == null) {
            collisionDict.put(o.getTag(), new HashMap<>());
        }

        o.setColType(type);
        collisionDict.get(o.getTag()).put(o, targets);
    }

    public HashMap<String, HashMap<Collidable, String[]>> getCollisionDict() {
        return collisionDict;
    }

    public void add(Collection<? extends Node> n) {
        batch.addAll(n);
    }

    public ArrayList<Node> getContent() {
        return batch;
    }

    @Override
    public void add(ArrayList<BasicNode> n) {
        for (BasicNode node : n) {
            injectInfo(node);
            batch.add(node.getNode());
        }
    }

}
