package leximir;

public class EditorDelacFactory {
	private EditorDelac dialog;

	public EditorDelac newEditorDelacDialog() {
		dialog = new EditorDelac();
		return dialog;
	}
}
