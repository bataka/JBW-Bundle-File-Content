
package mn.le.bataka.jbw.bundle.file.content.editor;

import mn.le.farcek.common.NamedContainer;
import mn.le.farcek.jbw.api.IAction;
import mn.le.farcek.jbw.api.IBundle;
import mn.le.farcek.jbw.api.module.JBWAbstractModule;

public class EditorModule extends JBWAbstractModule {

    public EditorModule() {
    }

    public EditorModule(IBundle bundle) {
        super(bundle);
    }

    @Override
    public void setupActions(NamedContainer<IAction> actions) {
        actions.add(new EditorActions(this).getActions());
    }

    @Override
    public String getName() {
        return "editor";
    }

}
