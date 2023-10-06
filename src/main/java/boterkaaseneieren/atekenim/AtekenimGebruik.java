package boterkaaseneieren.atekenim;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AtekenimGebruik extends JavaPlugin{

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        AtekenimListener atekenimListener = new AtekenimListener();
        Bukkit.getPluginManager().registerEvents(atekenimListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
