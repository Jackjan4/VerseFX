/*
 * Copyright 2016, Jan-Philipp Roslan, Alle Rechte vorbehalten
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.BoundingBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import javafx.scene.Group;
import org.dyn4j.dynamics.World;

/**
 * Abstract Level definition. The root can be used to modify the camera/scene
 * for the level itself However a real level implementation cannot directly
 * access the root
 *
 * @author Jackjan
 */
public abstract class Level implements Updateable, ObjectManager {

    private Group root;
    private ObjectBatch batch;

    private HashSet<BasicNode> garbage;
    private HashMap<BasicNode, BoundingBox> colQueue;

    public ObjectBatch initLevel(Group root) {
        this.root = root;
        batch = new ObjectBatch(this, null);
        init(batch);

        garbage = new HashSet<>();
        colQueue = new HashMap<>();

        root.getChildren().clear();
        root.getChildren().addAll(batch.getContent());

        return batch;
    }
    
    public ObjectBatch initLevel(Group root, World world) {
        this.root = root;
        batch = new ObjectBatch(this, world);
        init(batch);

        garbage = new HashSet<>();
        colQueue = new HashMap<>();

        root.getChildren().clear();
        root.getChildren().addAll(batch.getContent());

        return batch;
    }

    public HashSet<BasicNode> getGarbage() {
        return garbage;
    }

    protected abstract void init(ObjectCollector batch);

    @Override
    public void destroyObject(BasicNode o) {
        root.getChildren().remove(o.getNode());
        batch.getContent().remove(o.getNode());
        garbage.add(o);

    }

    @Override
    public ArrayList<Collidable> getCollisions(Collidable o) {
        ArrayList<Collidable> result = new ArrayList<>();

        if (!batch.getCollisionDict().containsKey(o.getTag()) || !batch.getCollisionDict().get(o.getTag()).containsKey(o)) {
            return result;
        }

        for (String target : batch.getCollisionDict().get(o.getTag()).get(o)) {

            // Target not in dict
            if (!batch.getCollisionDict().containsKey(target)) {
                continue;
            }

            for (Entry<Collidable, String[]> entry : batch.getCollisionDict().get(target).entrySet()) {
                if (o.isIntersecting(entry.getKey())) {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }

    @Override
    public void addObject(BasicNode o) {
        batch.add(o);
        root.getChildren().add(o.getNode());
    }

    @Override
    public void addToColQueue(BasicNode o, BoundingBox newPos) {
        colQueue.put(o, newPos);
    }

    @Override
    public final void tick(float deltaT) {

        for (BasicNode o : garbage) {
            batch.getCollisionDict().get(o.getTag()).remove((Collidable) o);
        }
        garbage.clear();

        processColQueue();

        update(deltaT);
    }

    private void processColQueue() {
        // Check collisions in col queue
        colqueue:
        for (Entry<BasicNode, BoundingBox> colEntry : colQueue.entrySet()) {
            HashMap<Collidable, String[]> lul = batch.getCollisionDict().get(colEntry.getKey().getTag());

            String[] targets = lul.get(colEntry.getKey());

            for (String s : targets) {
                for (Entry<Collidable, String[]> batchEntry : batch.getCollisionDict().get(s).entrySet()) {
                    if (batchEntry.getKey().isIntersecting(colEntry.getKey())) {
                        continue colqueue;
                    }

                }
            }

            colEntry.getKey().setX(colEntry.getValue().getX());
            colEntry.getKey().setY(colEntry.getValue().getY());
        }
    }

    protected abstract void update(float deltaT);
}
