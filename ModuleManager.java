package aboudi.client.module;

import aboudi.client.module.impl.FullbrightModule;
import aboudi.client.module.impl.SprintModule;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new SprintModule());
        modules.add(new FullbrightModule());
        modules.add(new aboudi.client.module.impl.FpsBoostModule());
    }

    public List<Module> getModules() { return modules; }

    public void tick() {
        for (Module m : modules) if (m.isEnabled()) m.onTick();
    }
}
