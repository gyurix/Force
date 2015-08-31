package Force;

import PluginReference.MC_Server;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;

public class MyPlugin extends PluginBase
{
  static MC_Server srv;

  public PluginInfo getPluginInfo()
  {
    PluginInfo inf = new PluginInfo();
    inf.ref = this;
    inf.description = "The totally working forcing, by Gyurix";
    return inf;
  }

  public void onStartup(MC_Server s) {
    srv = s;
    srv.registerCommand(new Command());
  }
}

/* Location:           D:\GitHub\Force.jar
 * Qualified Name:     Force.MyPlugin
 * JD-Core Version:    0.6.2
 */