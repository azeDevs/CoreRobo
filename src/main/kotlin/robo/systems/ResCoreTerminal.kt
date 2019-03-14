package robo.systems

import robo.abstracts.Form

interface ResCoreTerminal {
    companion object {

        val FORM_START = "\n" + Form.FIX + "◤◢◤◢◤◢◤◢  %s online  ◤◢◤◢◤◢◤◢" + Form.CODE

        val FORM_EXECUTE = "< " + ResIcons.IC_COMMAND + " %s >"
        val FORM_AUTHOR = "< %s %s >"
        val FORM_CHANNEL = "\n[ " + ResIcons.IC_CHANNEL + " %s ]"
        val FORM_SERVER = "[ " + ResIcons.IC_SERVER + " %s ] %s"

        val FORM_SETCHANNEL = ResIcons.IC_CONFIG + " debugChannel setVal to \"%s\" in \"%s\""
    }

}
