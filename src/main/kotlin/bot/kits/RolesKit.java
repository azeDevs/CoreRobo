package bot.kits;


import robo.abstracts.Kit;

public final class RolesKit extends Kit {

    public RolesKit() {
        super("RolesKit");
    }

    /*
    // TODO:RolesKit 1. onEvent(ServerMemberJoinEvent event)
    //
    */


//    @Override public void onEvent(ServerMemberJoinEvent event) {
//        super.onEvent(event);
//        // Fennah auto-role
//        if (event.getServer().getId() == ResRolesManager.ID_FENNAH_SERVER) {
//            event.getServer().getRoleById(ResRolesManager.ID_FENNAH_ROLE).ifPresent(role -> {
//                ServerUpdater updater = event.getServer().createUpdater();
//                updater.addRoleToUser(event.getUser(), role);
//                new Timer().schedule(new TimerTask() { @Override public void run() { updater.update(); cancel(); }
//                }, 6000); }); } // 1 minute
//    }
//
//    private Role getRoleFromKey(String key, Server server) {
//        for (Role role : server.getRoles()) if (key.equalsIgnoreCase(role.getName())) return role;
//        return null;
//    }
//
//    private String getRoleNamesList(UserMess mess) {
//        Collection<Role> roles = selfRolesData.getEntities(systems.getUserState(mess).getServer().getId());
//        StringBuilder sb = new StringBuilder();
//        roles.forEach(role -> sb.append("     " + Form.role(role) + "\n"));
//        if (roles.isEmpty()) sb.append("     " + "n/a" + "\n");
//        return sb.toString().substring(0, sb.toString().length()-1);
//    }
//
//    private void confirmRoleChange(UserMess mess, Role role) {
//        boolean adding = mess.hasModifiers(Modifier.ADD);
//        RoboPost post = getPostFlow(mess);
//        // Request user confirmation for role change
//        String formBefore = adding ? ResRolesManager.ADD_CONFIRM : ResRolesManager.REMOVE_CONFIRM;
//        post.addParagraph(String.format(formBefore, Form.role(role)));
//        post.sendFlowUpdate(true).thenAcceptAsync(postFlow -> {
//            post.popSections(1);
//            if (post.confirmed()) {
//                // Update User's role
//                ServerUpdater updater = mess.getServer().createUpdater();
//                if (adding) updater.addRoleToUser(mess.getUser(), role);
//                else updater.removeRoleFromUser(mess.getUser(), role);
//                // Send role change confirmation
//                String formAfter = adding ? ResRolesManager.ADD_COMPLETE : ResRolesManager.REMOVE_COMPLETE;
//                post.addParagraph(String.format(formAfter, Form.role(role)));
//                updater.update();
//            } else {
//                post.addParagraph("Assignment cancelled.");
//            }
//            post.sendFlowUpdate(false);
//        });
//    }
//
//    private void sendRoleAssignFlow(UserMess mess, Role role) {
//        // Check if requested role is valid
//        selfRolesData.getEntities(mess.getServer().getId()).stream().forEach(selfRole -> {
//            if (role.getId() == selfRole.getId()) {
//                // setVal as toggle if no Modifiers
//                if (!mess.hasModifier(Modifier.REMOVE) && !mess.hasModifier(Modifier.ADD) ) {
//                    if (Az.userHasRole(mess, role)) mess.addModifier(Modifier.REMOVE);
//                    else mess.addModifier(Modifier.ADD); }
//                // Send confirmation dialog
//                confirmRoleChange(mess, role);
//            }
//        });
//    }
//
//    private void sendRoleConfigFlow(UserMess mess, Role role) {
//        RoboPost post = getPostFlow(mess);
//        if (mess.hasModifiers(Modifier.ADD)) post.addParagraph(String.format(ResRolesManager.ADD_CONFIG_CONFIRM, Form.role(role)));
//        if (mess.hasModifiers(Modifier.REMOVE)) post.addParagraph(String.format(ResRolesManager.REMOVE_CONFIG_CONFIRM, Form.role(role)));
//
//        // Send choices
//        post.sendFlowUpdate(true).thenAcceptAsync(postFlow -> {
//            post.popSections(1);
//            if (post.confirmed()) { post.popSections(1);
//                if (mess.hasModifiers(Modifier.ADD)) {
//                    selfRolesData.addID(rc.getUserState(mess).getServer().getId(), role.getId());
//                    post.addParagraph(getRoleNamesList(mess));
//                    post.addParagraph(String.format(ResRolesManager.ADD_CONFIG_COMPLETE, Form.role(role))); }
//                else if (mess.hasModifiers(Modifier.REMOVE)) {
//                    selfRolesData.removeID(mess.getServer().getId(), role.getId());
//                    post.addParagraph(getRoleNamesList(mess));
//                    post.addParagraph(String.format(ResRolesManager.REMOVE_CONFIG_COMPLETE, Form.role(role)));
//                }
//            } else {
//                post.addParagraph("Configuration cancelled.");
//            }
//            post.sendFlowUpdate(false);
//        });
//    }
//
//    private RoboPost getPostFlow(UserMess mess) {
//        RoboPost post = new RoboPost(mess, ResRolesManager.IC_ROLES, "Self-assignable RolesKit");
//        post.setThumbnailWithServerIcon();
//        post.setRespondLoudly(true);
//        post.setFocusRequired(true);
//        post.setColorDefault(ResColors.ROLES);
//        post.addParagraph(getRoleNamesList(mess));
//        return post;
//    }
//
//    // ————————————————
//    // COMMANDS
//    //
//
//    private void cmdRole(UserMess mess) {
//        Role role = null;
//        if (mess.hasParameters(1)) role = getRoleFromKey(mess.getParam().toString(), mess.getServer());
//        if (role == null) {
//            RoboPost post = getPostFlow(mess);
//            post.addParagraph("All roles listed are available for self-assignment.");
//            post.addParagraph(ResRolesManager.USER_HELP0);
//            if(mess.hasModifier(Modifier.CONFIG)) {
//                post.addParagraph(ResRolesManager.ADMIN_HELP0);
//                post.addParagraph(ResRolesManager.ADMIN_HELP1);
//            }
//            post.sendPostUpdate();
//        } else { // 1 param
//            if (mess.hasModifier(Modifier.CONFIG)) sendRoleConfigFlow(mess, role);
//            else sendRoleAssignFlow(mess, role);
//        }
//    }
//
//    public class CommandRole extends Command<RolesKit> {
//        CommandRole(RolesKit mod) { super(mod, "ROLE", ENABLED, "Change your roles", ResRolesManager.USER_GUIDE);
//        setRequiredPermissions(MANAGE_ROLES); }
//        @Override public void execute(UserMess mess) { getParentModule().cmdRole(mess); }
//    }


}
