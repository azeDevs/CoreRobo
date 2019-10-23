package robo.components;

import org.javacord.api.entity.message.embed.EmbedBuilder;

public class RoboPost extends EmbedBuilder {

//    public final static String FORM_HEADER = "\n\n%s   **%s**";
//    public final static String FORM_PARAGRAPH = "\n\n%s";
//
//    protected UserMess mess;
//    protected Message postMessage;
//    private boolean focusRequired;
//
//    private String postIcon;
//    private String postHead;
//    private String postFoot;
//    private Color postColor;
//    private RoboCore rc;
//    private List<String> sections;
//
//    /*
//    //=========================================
//    //  #Post
//    //=========================================
//    */
//
//    public RoboPost(UserMess userMess) { this(userMess, ResIcons.IC_FOUNDER, "Test Post");  }
//    public RoboPost(UserMess userMess, String postHead) { this(userMess, ResIcons.IC_FOUNDER, postHead);  }
//    public RoboPost(UserMess userMess, String postIcon, String postHead) {
//        // #Post
//        this.postColor = ResColors.INVIS;
//        this.postIcon = postIcon;
//        this.postHead = postHead;
//        this.postFoot = "";
//        this.focusRequired = false;
//        this.respondLoudly = false;
//        this.sections = new ArrayList<>();
//        this.mess = userMess;
//        this.rc = userMess.robo();
//        setColor(ResColors.INVIS);
//        setImage(new File(HOME_DIR + "/src/main/resources/breaker.png"));
//        setFooterWithHelpIcon(FOOT_MORE_INFO);
//        // #PostFlow
//        this.choices = new ArrayList<>();
//        this.picks = new ArrayList<>();
//    }
//
//    public RoboPost setFocusRequired(boolean flag) { this.focusRequired = flag; return this; }
//    public RoboPost setRespondLoudly(boolean flag) { this.respondLoudly = flag; return this; }
//    public boolean isFocusRequired() { return focusRequired; }
//
//    public RoboPost setColor(Color color) {
//        super.setColor(color);
//        return update();
//    }
//
//    public RoboPost setColorDefault(Color color) {
//        this.postColor = color;
//        super.setColor(postColor);
//        return update();
//    }
//
//    public RoboPost resetToDefaultColor() {
//        super.setColor(postColor);
//        return update();
//    }
//
//    public RoboPost setImage(UserState user) { setImage(user.getUser().getAvatar()); return this; }
//    public RoboPost setImage(User user) { setImage(user.getAvatar()); return this; }
//    public RoboPost setImage(String fileName, String extension) {
//        setImage(new File(HOME_DIR + "/src/main/resources/"+fileName+"."+extension));
//        return this;
//    }
//
//    // Append Standard Text
//
//    public RoboPost setTitle(String emoji, String title) {
//        this.postIcon = emoji;
//        this.postHead = title;
//        return update();
//    }
//
//    public RoboPost setPostFoot(String postFoot) {
//        this.postFoot = postFoot;
//        return update();
//    }
//
//    public RoboPost addHeader(String emoji, String text) { sections.add(String.format(FORM_HEADER, emoji, text)); return update(); }
//    public RoboPost insertHeader(int index, String emoji, String text) { sections.add(Util.clampInt(index, 0, sections.size()-1), String.format(FORM_HEADER, emoji, text)); return update(); }
//
//    public RoboPost addParagraph(String text) { sections.add(String.format(FORM_PARAGRAPH, text)); return update(); }
//    public RoboPost insertParagraph(int index, String text) { sections.add(Util.clampInt(index, 0, sections.size()-1), String.format(FORM_PARAGRAPH, text)); return update(); }
//
//    public RoboPost addStyleBlock(String style, String text) { return addParagraph(String.format("%s%s```", style, text)); }
//
//    public RoboPost addText(String text) { addText(text, null, null); return update(); }
//    public RoboPost addText(String text, String oldChars, String newChars) { sections.add(replaceCharacters(text, oldChars, newChars)); return update(); }
//
//
//    // Set Icons
//
//    public RoboPost setAttachedImage(String fileName) { setImage(Util.getImage(fileName)); return update(); }
//
//    public RoboPost setThumbnailWithImageIcon(String fileName) { setThumbnail(Util.getImage(fileName)); return update(); }
//    public RoboPost setThumbnailWithoutIcon() { setThumbnail(""); return update(); }
//    public RoboPost setThumbnailWithRoboIcon() { setThumbnail(rc.api().getYourself().getAvatar()); return update(); }
//    public RoboPost setThumbnailWithUserIcon() { setThumbnail(mess.getUser().getAvatar()); return update(); }
//    public RoboPost setThumbnailWithServerIcon() {
//        if (mess.getMessage().isPrivate()) return setThumbnailWithUserIcon();
//        mess.getServer().getIcon().ifPresent(icon -> setThumbnail(icon)); return update();
//    }
//
//    public RoboPost setFooterWithImageIcon(String fileName, String text) { setFooter(Form.vars(mess, text), Util.getImage(fileName)); return update(); }
//    public RoboPost setFooterWithoutIcon(String text) { setFooter(Form.vars(mess, text), ""); return update(); }
//    public RoboPost setFooterWithRoboIcon(String text) { setFooter(Form.vars(mess, text), rc.api().getYourself().getAvatar()); return update(); }
//    public RoboPost setFooterWithUserIcon(String text) { setFooter(Form.vars(mess, text), ""/*mess.getUser().getAvatar()*/); return update(); }
//    public RoboPost setFooterWithHelpIcon(String text) { setFooter(Form.vars(mess, text), new File(HOME_DIR + "/src/main/resources/ic_information.png")); return update(); }
//    public RoboPost setFooterWithServerIcon(String text) {
//        if (mess.getMessage().isPrivate() && rc.getUserState(mess).getServer() == null) return setFooterWithUserIcon(text);
//        else setFooter(Form.vars(mess, text), rc.getUserState(mess).getServerIcon()); return update();
//    }
//
//
//    // Reformat Text
//
//    private String replaceCharacters(String text, String oldChars, String newChars) {
//        text = Form.vars(mess, text);
//        if (oldChars == null || newChars == null || oldChars.length() != newChars.length()) return text;
//        for (int i = 0; i < oldChars.length(); i++) text = text.replaceAll(Pattern.quote(oldChars.substring(i, i + 1)), newChars.substring(i, i + 1));
//        return text;
//    }
//
//    public RoboPost addQuote(String text) {
//        if (text.isEmpty()) return this;
//        String[] allQuotes = { "\"", "‘", "’", "“", "”", "❛", "❜" };
//        boolean left = false; boolean right = false;
//        for (int i = 0; i < allQuotes.length; i++) {
//            if (text.substring(0, 1).equalsIgnoreCase(allQuotes[i])) left = true;
//            if (text.substring(text.length() - 1).equalsIgnoreCase(allQuotes[i])) right = true; }
//        if (left && right) text = text.substring(1, text.length() - 1);
//        return addParagraph("*❝ " + text + " ❞*");
//    }
//
//    public RoboPost removeSection(int index) {
//        index = Math.min(sections.size(), index);
//        sections.remove(Math.min(Math.max(index, 0), sections.size()-1));
//        return update();
//    }
//
//    public RoboPost popSections(int amount) {
//        amount = Math.min(sections.size(), amount);
//        for (int i = 0; i < amount; i++) sections.remove(sections.size()-1);
//        return update();
//    }
//
//    public RoboPost clearSections() {
//        sections.clear();
//        return update();
//    }
//
//
//    // Utility
//
//    private RoboPost update() {
//        StringBuilder description = new StringBuilder(Form.vars(mess, String.format(FORM_HEADER, postIcon, postHead)));
//        if (!sections.isEmpty()) for (String section : sections) description.append(Form.vars(mess, section));
//        if (!postFoot.isEmpty()) description.append(Form.vars(mess, String.format(FORM_PARAGRAPH, postFoot)));
//        setDescription(description.toString()); return this;
//    }
//
//
//    // Send Post
//
//    public CompletableFuture<Message> sendPost() {
//        if (mess.hasModifier(Modifier.CONFIG)) setFooterWithServerIcon("$SERVER    "+ResIcons.IC_CONFIG+"    CONFIGURATION");
//
//        int index = new Random().nextInt(HELPFUL_TIPS.length);
//        String out = HELPFUL_TIPS[index];
//        if (mess.hasModifier(Modifier.HELP)) setFooterWithHelpIcon("Tip: " + out);
//
//        // Make a new Post if Loud/Private
//        if (respondLoudly || mess.hasModifier(Modifier.LOUD) || mess.getMessage().isPrivate()) {
//            return rc.post().respond(mess, update());
//        }
//
//        // update existing Post if non-private and already exists
//        if (!mess.getMessage().isPrivate() && postMessage != null) {
//            CompletableFuture<Message> completableFuture = new CompletableFuture<>();
//            postMessage.edit(update()).thenAcceptAsync(aVoid -> completableFuture.complete(postMessage));
//            return completableFuture;
//        }
//
//        // Send a private Post and add reaction if non-loud/non-private
//        mess.getMessage().addReaction(this.postIcon);
//        return rc.post().respondPrivately(mess, update());
//    }
//
//    public CompletableFuture<Message> sendPostUpdate() {
//        if (postMessage == null) return sendPost();
//        CompletableFuture<Message> completableFuture = new CompletableFuture<>();
//        postMessage.edit(update()).thenAcceptAsync(aVoid -> completableFuture.complete(postMessage));
//        return completableFuture;
//    }
//
//    public void sendAttachment(File... files) {
//        rc.api().getTextChannelById(mess.getChannelID()).ifPresent(channel -> channel.sendMessage(files));
//    }
//
//
//
//    /*
//    //=========================================
//    //  #PostFlow
//    //=========================================
//    */
//
//    protected final static int MAX_CHOICES = 8;
//    private final static String[] MENUCHARS = {"X", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
//    private final static String[] MENUICONS = {"\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9", "\uD83C\uDDEA", "\uD83C\uDDEB", "\uD83C\uDDEC", "\uD83C\uDDED", "\uD83C\uDDEE", "\uD83C\uDDEF"};
//    private final static String ACCEPTED = "✅";
//    private final static String DECLINED = "❌";
//    private final static String FORM_CHOICE = "   %s   %s\n\n";
//
//    private ReactionAddListener buttonsListener;
//    private MessageCreateListener respondListener;
//    private List<PostChoice> choices;
//    private List<PostChoice> picks;
//
//    public boolean addChoice(String choiceText) { return addChoice(new PostChoice(MENUICONS[choices.size()], choiceText)); }
//    public boolean addChoice(String choiceEmoji, String choiceText) { return addChoice(new PostChoice(choiceEmoji, choiceText)); }
//    public boolean addChoice(PostChoice choice) {
//        if (choices.size() == MAX_CHOICES && (!choice.emoji.equals(DECLINED) && !choice.text.equals("CANCEL"))) {
//            Logs.addLog("PostFlow", MAX_CHOICES + " choices maximum reached");
//            Logs.dumpWarning("PostFlow");
//            return false;
//        }
//        return choices.add(choice);
//    }
//
//
//    public CompletableFuture<RoboPost> sendFlowUpdate(boolean keepAlive) {
//        CompletableFuture<RoboPost> postFuture = new CompletableFuture<>();
//        if (serverFocusValidated(true)) {
//            if(keepAlive) addChoicesSection();
//            else setFooterWithImageIcon("ic_completed", FOOT_RESPONSE_COMPLETE).setPostFoot("");
//
//            if (postMessage == null || postMessage.isPrivate()) sendPost().thenAcceptAsync(message -> initPickListeners(postFuture, message));
//            else postMessage.removeAllReactions().thenAcceptAsync((a1) -> updatePostWithEdits().thenAcceptAsync(a2 -> {
//                if(keepAlive) initPickListeners(postFuture, postMessage);
//                else postFuture.complete(this);
//            }));
//        } else postFuture.complete(this);
//        return postFuture;
//    }
//
//    protected boolean serverFocusValidated(boolean inform) {
//        if (mess.getMessage().isPrivate())
//            if (isFocusRequired() && mess.robo().getUserState(mess).getServer() == null) {
//                if (inform) Util.informExecutionFailure(mess, "$COMMAND must be initiated from a Discord server.");
//                return false;
//            } else setFooterWithServerIcon(FOOT_RESPONSE_USER);
//        else setFooterWithUserIcon(FOOT_RESPONSE_SERVER);
//        return true;
//    }
//
//    private void initPickListeners(CompletableFuture<RoboPost> postFuture, Message message) {
//        this.postMessage = message;
//        addButtonsRecursively(0);
//        if (buttonsListener != null) postMessage.removeListener(ReactionAddListener.class, buttonsListener);
//        if (respondListener != null) mess.getUser().removeListener(MessageCreateListener.class, respondListener);
//        this.buttonsListener = postMessage.addReactionAddListener(event -> {
//            for (int i = 0; i < choices.size(); i++) {
//                if (validUserPick(event, i)) {
//                    finalizePick(postFuture, i);
//                }
//            }
//        }).getListener();
//        this.respondListener = mess.getUser().addMessageCreateListener(event -> {
//            for (Integer i = 1; i < choices.size()+1; i++)
//                if (event.getMessage().getContent().equalsIgnoreCase(MENUCHARS[0])) {
//                    event.getMessage().delete(); finalizePick(postFuture, choices.size()-1);
//                } else if (event.getMessage().getContent().equalsIgnoreCase(MENUCHARS[i])) {
//                    event.getMessage().delete(); finalizePick(postFuture, i-1);
//                } else if (event.getMessage().getContent().equalsIgnoreCase(i.toString())) {
//                    event.getMessage().delete(); finalizePick(postFuture, i-1);
//                }
//        }).getListener();
//    }
//
//    private void finalizePick(CompletableFuture<RoboPost> postFuture, int i) {
//        resetToDefaultColor();
//        picks.add(choices.get(i));
//        if (lastPick().index == choices.size()-1 && !mess.getMessage().isPrivate()) {
//            sendFlowUpdate(false);
//        }
//        choices = new ArrayList<>();
//        postFuture.complete(this);
//    }
//
//    private CompletableFuture<RoboPost> updatePostWithEdits() {
//        CompletableFuture<RoboPost> completableFuture = new CompletableFuture<>();
//        postMessage.edit(this).thenAcceptAsync(aVoid -> completableFuture.complete(this));
//        return completableFuture;
//    }
//
//    private boolean validUserPick(ReactionAddEvent event, int i) {
//        return event.getEmoji().equalsEmoji(choices.get(i).emoji) && event.getUser().getId() == mess.getUserID();
//    }
//
//    private void addChoicesSection() {
//        if (choices.isEmpty()) {
//            addChoice(ACCEPTED, "ACCEPT");
//            setColor(ResColors.GREEN);
//        } else setColor(ResColors.BLUE);
//        addChoice(DECLINED, "CANCEL");
//        StringBuilder sb = new StringBuilder();
//        for (PostChoice choice : choices) sb.append(String.format(FORM_CHOICE, choice.emoji, choice.text));
//        setPostFoot(sb.toString());
//    }
//
//    private void addButtonsRecursively(int index) {
//        if (index < choices.size()) postMessage.addReaction(choices.get(index).emoji)
//                .thenAcceptAsync(aVoid -> addButtonsRecursively(index + 1));
//    }
//
//
//    // Utility methods
//
//    public boolean confirmed() {
//        if (picks.isEmpty()) return true;
//        if (lastPick().index == 0) return true;
//        return false;
//    }
//
//    public boolean lastSelected(int selected) {
//        return picks.isEmpty() ? false : lastPick().index == selected - 1;
//    }
//
//    protected void removeReactionButtons() {
//        if (postMessage == null) return;
//        if (!postMessage.isPrivate()) postMessage.removeAllReactions();
//        else {
//            List<String> choiceEmojis = new ArrayList<>();
//            choices.stream().forEach(postChoice -> choiceEmojis.add(postChoice.emoji));
//            choiceEmojis.stream().forEach(emoji -> postMessage.removeOwnReactionByEmoji(emoji));
//        }
//        choices.clear();
//    }
//
//
//    // #PostFlow class objects
//
//    public Message getPostMessage() { return postMessage; }
//
//    public List<PostChoice> getPicks() { return picks; }
//    protected PostChoice lastPick() {
//        return picks.get(picks.size()-1);
//    }
//    public class PostChoice {
//        private int index;
//        private String emoji, text;
//        public PostChoice(String emoji, String text) {
//            this.index = choices.size();
//            this.emoji = emoji;
//            this.text = Form.vars(mess, text).toUpperCase();
//        }
//        public int getIndex() { return index; }
//        public String getEmoji() { return emoji; }
//        public String getText() { return text; }
//    }
//
//
//    /*
//    //=========================================
//    //  #PostFind
//    //=========================================
//    */
//
//    private void sendFailedResultsPost(String parameter, String lackType) {
//        addParagraph(String.format(FORM_FAILED_SEARCH, parameter, lackType));
//        sendFlowUpdate(false);
//    }
//
//    public CompletableFuture<UserState> findUser(Param param) {
//        List<UserState> states = new ArrayList<>();
//        mess.getServer().getMembers().forEach(user -> states.add(rc.getUserState(user)));
//        return findUser(param, states);
//    }
//    public CompletableFuture<UserState> findUser(Param param, List<UserState> users) {
//        CompletableFuture<UserState> selection = new CompletableFuture<>();
//        Finder<UserState> finder = new Finder<>(entity -> new Result<>(entity, entity.getUserName()));
//        finder.addEntities(users);
//        List<Result<UserState>> results = finder.getResultsFrom(param);
//        // return result
//        if (results.size() == 1) { selection.complete(results.get(0).getObject()); }
//        // send options flow
//        if (results.size() > 1 && results.size() <= MAX_CHOICES) {
//            for (Result<UserState> result : results) addChoice(Form.user(result.getObject(), mess.getServer()));
//            sendFlowUpdate(true).whenCompleteAsync((postFlow, t) -> { Logs.error(t); setPostFoot(""); removeReactionButtons();
//                if (postFlow.lastPick().getIndex() <= results.size()) selection.complete(results.get(postFlow.lastPick().getIndex()).getObject()); }); }
//        // request try again
//        if (results.isEmpty()) { this.sendFailedResultsPost(param.toString(), "returned **no results**"); selection.cancel(true);
//        } else if (results.size() > MAX_CHOICES) { this.sendFailedResultsPost(param.toString(), "returned **too many results**"); selection.cancel(true); }
//        return selection;
//    }
//
//
//
//    public CompletableFuture<ServerTextChannel> findChannel(Param param) { return findChannel(param, mess.getServer().getTextChannels()); }
//    public CompletableFuture<ServerTextChannel> findChannel(Param param, List<ServerTextChannel> channels) {
//        CompletableFuture<ServerTextChannel> selection = new CompletableFuture<>();
//        Finder<ServerTextChannel> finder = new Finder<>(entity -> new Result<>(entity, entity.getName()));
//        finder.addEntities(channels);
//        List<Result<ServerTextChannel>> results = finder.getResultsFrom(param);
//        // return result
//        if (results.size() == 1) { selection.complete(results.get(0).getObject()); }
//        // send options flow
//        if (results.size() > 1 && results.size() <= MAX_CHOICES) {
//            for (Result<? extends Mentionable> result : results) addChoice(Form.mentionable(result.getObject(), mess.getServer()));
//            sendFlowUpdate(true).whenCompleteAsync((postFlow, t) -> { Logs.error(t); setPostFoot(""); removeReactionButtons();
//                if (postFlow.lastPick().getIndex() <= results.size()) selection.complete(results.get(postFlow.lastPick().getIndex()).getObject()); }); }
//        // request try again
//        if (results.isEmpty()) { this.sendFailedResultsPost(param.toString(), "returned **no results**"); selection.cancel(true);
//        } else if (results.size() > MAX_CHOICES) { this.sendFailedResultsPost(param.toString(), "returned **too many results**"); selection.cancel(true); }
//        return selection;
//    }
//
//
//    public CompletableFuture<Role> findRole(Param param, String uniqueCharacter) {
//        CompletableFuture<Role> selection = new CompletableFuture<>();
//        Finder<Mentionable> findEntity = new Finder<>(entity -> { if (entity instanceof Role) return new Result<>(entity, ((Role) entity).getName());else return null; });
//        // get all entities and filter them
//        findEntity.addEntities(mess.getServer().getRoles().stream().collect(Collectors.toList()));
//        List<Result<Mentionable>> results = findEntity.getResultsFrom(param).stream().filter(mentionableResult -> { if (uniqueCharacter == null ||  uniqueCharacter.isEmpty()) return true;return mentionableResult.getName().contains(uniqueCharacter); }).collect(Collectors.toList());
//        // return result
//        if (results.size() == 1) { selection.complete((Role) results.get(0).getObject()); }
//        // send options flow
//        if (results.size() > 1 && results.size() <= MAX_CHOICES) {
//            for (Result<? extends Mentionable> result : results) addChoice(Form.mentionable(result.getObject(), mess.getServer()));
//            sendFlowUpdate(true).whenCompleteAsync((postFlow, t) -> { Logs.error(t); setPostFoot(""); removeReactionButtons();
//            if (postFlow.lastPick().getIndex() <= results.size()) selection.complete((Role) results.get(postFlow.lastPick().getIndex()).getObject()); }); }
//        // request try again
//        if (results.isEmpty()) { this.sendFailedResultsPost(param.toString(), "returned **no results**"); selection.cancel(true);
//        } else if (results.size() > MAX_CHOICES) { this.sendFailedResultsPost(param.toString(), "returned **too many results**"); selection.cancel(true); }
//        return selection;
//    }

}
