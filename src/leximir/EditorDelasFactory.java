package leximir;


public class EditorDelasFactory {
	private EditorDelas dialog;

	public EditorDelas newEditorDelasDialog() {
		dialog = new EditorDelas();
		return dialog;
	}
}
