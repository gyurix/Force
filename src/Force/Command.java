package Force;

import PluginReference.MC_Command;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import WrapperObjects.Entities.PlayerWrapper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import joebkt.EntityPlayer;
import joebkt.Packet_IncomingChatMessage;
import joebkt.PlayerConnection;
import org.apache.commons.lang3.StringUtils;

public class Command
  implements MC_Command
{
  public List<String> getAliases()
  {
    List aliases = new ArrayList();
    aliases.add("sudo");
    aliases.add("force");
    return aliases;
  }

  public String getCommandName()
  {
    return "sudo";
  }

  public String getHelpLine(MC_Player arg0)
  {
    return null;
  }

  public List<String> getTabCompletionList(MC_Player arg0, String[] arg1)
  {
    return null;
  }

  public void handleCommand(MC_Player plr, String[] args)
  {
    if (args.length < 2) {
      plr.sendMessage("§cYou didn't give the forced message or the target player.");
      return;
    }
    MC_Player target = MyPlugin.srv.getOnlinePlayerByName(args[0]);
    if (target == null) {
      plr.sendMessage("§cTarget player not found.");
    }
    PlayerConnection c = ((PlayerWrapper)target).plr.plrConnection;
    try {
      String s = StringUtils.join(args, " ", 1, args.length);
      c.handlePacketPlayerInputString(new Packet_IncomingChatMessage(s));
      if (plr == null)
        System.out.println(target.getName() + " succesfully forced to enter message: " + s);
      else
        plr.sendMessage("§e" + target.getName() + "§a succesfully forced to enter message: §f" + s);
    }
    catch (Throwable e) {
      plr.sendMessage("§cError on forcing, see the console for details.");
      e.printStackTrace();
    }
  }

  public boolean hasPermissionToUse(MC_Player plr)
  {
    return plr.hasPermission("force.use");
  }
}

/* Location:           D:\GitHub\Force.jar
 * Qualified Name:     Force.Command
 * JD-Core Version:    0.6.2
 */