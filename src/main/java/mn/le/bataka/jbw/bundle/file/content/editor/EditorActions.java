package mn.le.bataka.jbw.bundle.file.content.editor;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mn.le.farcek.common.NamedContainer;
import mn.le.farcek.jbw.api.IAction;
import mn.le.farcek.jbw.api.IModule;
import mn.le.farcek.jbw.api.action.ActionParamInfo;
import mn.le.farcek.jbw.api.action.ActionResult;
import mn.le.farcek.jbw.api.action.IActionParam;
import mn.le.farcek.jbw.api.action.IActionResult;
import mn.le.farcek.jbw.api.action.JBWAbstractAction;
import mn.le.farcek.jbw.api.action.JBWPackageActions;

public class EditorActions extends JBWPackageActions {

    public EditorActions(IModule module) {
        super(module);
    }

    @Override
    public void setupActions(NamedContainer<IAction> actions, IModule module) {
        actions.add(new upload().setModule(module));
        actions.add(new browse().setModule(module));
    }

    public class upload extends JBWAbstractAction {

        @Override
        public IActionResult execut(IActionParam actionParam) {
            String rsName = "";
            try {
                rsName = getContext().getResourceManager().create(actionParam, "upload");
            } catch (IOException ex) {
                Logger.getLogger(EditorActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new ActionResult("//upload")
                    .add("CKEditorFuncNum", actionParam.get("CKEditorFuncNum"))
                    .add("imageName", rsName);
        }

        @Override
        public String getRequaredRole() {
            return "admin";
        }

        @Override
        public ActionType getActionType() {
            return ActionType.Other;
        }

        @Override
        public NamedContainer<ActionParamInfo> getRequaredActionParams() {
            return new NamedContainer<>();
        }

    }

    public class browse extends JBWAbstractAction {

        @Override
        public IActionResult execut(IActionParam actionParam) {
            List<Result> result = new ArrayList<>();
            File dir = getContext().getConfig().getDirOfResource();
            final String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp", "jpg", "jpeg" // and other formats you need
            };
            // filter to identify images based on their extensions
            final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

                @Override
                public boolean accept(final File dir, final String name) {
                    for (final String ext : EXTENSIONS) {
                        if (name.endsWith("." + ext)) {
                            return (true);
                        }
                    }
                    return (false);
                }
            };
            File[] listFiles = dir.listFiles(IMAGE_FILTER);
            for (File file : listFiles) {
                Result r = new Result();
                r.image = getContext().getResourceManager().imageUrl(file.getName());
                r.thumb = getContext().getResourceManager().imageThumbnailUrl(file.getName(), 150, 150);
                r.folder = "Default";
                result.add(r);
            }

            return new ActionResult(
                    "bundle://core/json")
                    .add("result", result);
        }

        @Override
        public IAction.ActionType getActionType() {
            return IAction.ActionType.Other;
        }

        @Override
        public NamedContainer<ActionParamInfo> getRequaredActionParams() {
            return new NamedContainer<>();
        }

        @Override
        public String getRequaredRole() {
            return "admin";
        }

    }

    class Result {

        public String image;
        public String thumb;
        public String folder;
    }

}
