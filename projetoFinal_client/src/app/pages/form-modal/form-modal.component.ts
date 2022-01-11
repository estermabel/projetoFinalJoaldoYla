import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-form-modal',
  templateUrl: './form-modal.component.html',
  styleUrls: ['./form-modal.component.css']
})
export class FormModalComponent implements OnInit {

  constructor(
    public activeModal: NgbActiveModal,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
  }

  openFormModal() {
    const modalRef = this.modalService.open(FormModalComponent);

    modalRef.result.then((result) => {
      console.log(result);
    }).catch((error) => {
      console.log(error);
    });
  }

  closeModal() {
    this.activeModal.close('Modal Closed');
  }
}
