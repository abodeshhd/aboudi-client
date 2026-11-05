package aboudi.client.module.impl;

import aboudi.client.module.Module;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.ParticlesMode;

public class FpsBoostModule extends Module {
    private int viewDist, simDist, biomeBlend, maxFps;
    private double entityDist;
    private boolean shadows;
    private ParticlesMode particles;
    private CloudRenderMode clouds;

    public FpsBoostModule() { super("FPS Boost"); }

    @Override
    protected void onEnable() {
        GameOptions o = mc.options;
        viewDist = o.getViewDistance().getValue();
        simDist = o.getSimulationDistance().getValue();
        biomeBlend = o.getBiomeBlendRadius().getValue();
        maxFps = o.getMaxFps().getValue();
        entityDist = o.getEntityDistanceScaling().getValue();
        shadows = o.getEntityShadows().getValue();
        particles = o.getParticles().getValue();
        clouds = o.getCloudRenderMode().getValue();

        o.getViewDistance().setValue(6);
        o.getSimulationDistance().setValue(5);
        o.getBiomeBlendRadius().setValue(0);
        o.getMaxFps().setValue(260);
        o.getEntityDistanceScaling().setValue(0.5);
        o.getEntityShadows().setValue(false);
        o.getParticles().setValue(ParticlesMode.MINIMAL);
        o.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        o.write();
    }

    @Override
    protected void onDisable() {
        GameOptions o = mc.options;
        o.getViewDistance().setValue(viewDist);
        o.getSimulationDistance().setValue(simDist);
        o.getBiomeBlendRadius().setValue(biomeBlend);
        o.getMaxFps().setValue(maxFps);
        o.getEntityDistanceScaling().setValue(entityDist);
        o.getEntityShadows().setValue(shadows);
        o.getParticles().setValue(particles);
        o.getCloudRenderMode().setValue(clouds);
        o.write();
    }
}
